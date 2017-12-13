import javafx.application.Application;
import java.util.*;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ExpressionEditor extends Application {
	public static void main (String[] args) {
		launch(args);
	}
	Expression root;
	/**
	 * Mouse event handler for the entire pane that constitutes the ExpressionEditor
	 */
	private static class MouseEventHandler implements EventHandler<MouseEvent>{
		MouseEventHandler (Pane pane, CompoundExpression rootExpression){
		root=rootExpression;
		System.out.println(rootExpression);
		this._Label=new Label(rootExpression.getString());
		}
		private CompoundExpression root;
		private Label _Label;
		double _lastX, _lastY;
				
			public void handle(MouseEvent event) {
				Expression selected = null;
				final double sceneX = event.getSceneX();
				final double sceneY = event.getSceneY();
				
				if(event.getEventType()==MouseEvent.MOUSE_PRESSED) {
					if(selected==null)
					{
						selected=root.getMostSpecificFocus(event.getSceneX(),event.getSceneY())
								.getMostSpecificFocus(event.getSceneX(),event.getSceneY());
					}
					else{
						for(Expression child: selected.getSubExpression())
						{
							 Expression temp =child.getMostSpecificFocus(event.getSceneX(),event.getSceneY());
							 if(temp!=null) {
								 selected= temp;
							 }

						}
						selected=null;
					}
				}
				else if (event.getEventType()==MouseEvent.MOUSE_DRAGGED) {
					_label.setTranslateX(_label.getTranslateX()+(sceneX-_lastX));
					_label.setTranslateY(_label.getTranslateY()+(sceneY-_lastY));
					
				}
				else if(event.getEventType()==MouseEvent.MOUSE_RELEASED) {
					_label.setLayoutX(_label.getLayoutX() + _label.getTranslateX());
					_label.setLayoutY(_label.getLayoutY() + _label.getTranslateY());
				}
				_lastX= sceneX;
				_lastY= sceneY;
			}
		}
	/**
	 * Size of the GUI
	 */
	private static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 250;

	/**
	 * Initial expression shown in the textbox
	 */
	private static final String EXAMPLE_EXPRESSION = "x+2";

	/**
	 * Parser used for parsing expressions.
	 */
	private final ExpressionParser expressionParser = new SimpleExpressionParser();

	@Override
	public void start (Stage primaryStage) {
		primaryStage.setTitle("Expression Editor");

		// Add the textbox and Parser button
		final Pane queryPane = new HBox();
		final TextField textField = new TextField(EXAMPLE_EXPRESSION);
		final Button button = new Button("Parse");
		queryPane.getChildren().add(textField);
		final Pane expressionPane = new Pane();

		// Add the callback to handle when the Parse button is pressed	
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				// Try to parse the expression
				try {
					// Success! Add the expression's Node to the expressionPane
					final Expression expression = expressionParser.parse(textField.getText(), true);
					expressionPane.getChildren().clear();
					expressionPane.getChildren().add(expression.getNode());
					expression.getNode().setLayoutX(WINDOW_WIDTH/4);
					expression.getNode().setLayoutY(WINDOW_HEIGHT/2);
					// If the parsed expression is a CompoundExpression, then register some callbacks

					//queryPane.getChildren().add(textField);					// If the parsed expression is a CompoundExpression, then register some callbacks

					if (expression instanceof CompoundExpression) {
						((Pane) expression.getNode()).setBorder(Expression.NO_BORDER);
						final MouseEventHandler eventHandler = new MouseEventHandler(expressionPane, (CompoundExpression) expression);
						expressionPane.setOnMousePressed(eventHandler);
						expressionPane.setOnMouseDragged(eventHandler);
						expressionPane.setOnMouseReleased(eventHandler);
					}
				} catch (ExpressionParseException epe) {
					System.out.println("hi");
					// If we can't parse the expression, then mark it in red
					textField.setStyle("-fx-text-fill: red");
					queryPane.getChildren().add(new Label(textField.getText()));
					queryPane.getChildren().add(new Label("TEST"));
					HBox test = new HBox();
					Label tester = new Label("test");
					test.getChildren().add(tester);
				
					
				}
			}
		});
		queryPane.getChildren().add(button);

		// Reset the color to black whenever the user presses a key
		textField.setOnKeyPressed(e -> textField.setStyle("-fx-text-fill: black"));
		
		final BorderPane root = new BorderPane();
		root.setTop(queryPane);
		root.setCenter(expressionPane);

		primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
		primaryStage.show();
	}
}
