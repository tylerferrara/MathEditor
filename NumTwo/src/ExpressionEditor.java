import javafx.application.Application;
import java.util.*;

import javafx.geometry.Bounds;
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

	/**
	 * Mouse event handler for the entire pane that constitutes the ExpressionEditor
	 */
	private static class MouseEventHandler implements EventHandler<MouseEvent> {
		private Pane pane;
		private CompoundExpression rootExpression;
		private Node focus;
		int focusDepth;
		MouseEventHandler (Pane pane_, CompoundExpression rootExpression_) {
			pane = pane_;
			rootExpression = rootExpression_;
			focus = null;
			focusDepth = -1;
		}
		
		public boolean contains(Node n, Double x, Double y) {
			Bounds boundsInScene = n.localToScene(n.getBoundsInLocal());
			if(x >= boundsInScene.getMinX() && x <= boundsInScene.getMaxX() && y >= boundsInScene.getMinY() && y <= boundsInScene.getMaxY()) {
				return true;
			} else {
				return false;
			}
		}
		

		public void focusHelper (Node focus, MouseEvent event) {
			Node newFocus = null;
			HBox myFocus = (HBox) focus;
			for(int i = 0; i < myFocus.getChildren().size(); i++) {
				if(contains(myFocus.getChildren().get(i), event.getSceneX(), event.getSceneY())) {
					newFocus = myFocus.getChildren().get(i);
				}
			}
			if( newFocus != null) {
				// HERE IS OUR NEW FOCUS!!
				focus.setStyle("");
				focus = newFocus;
				focusDepth = focusDepth+1;
				focus.setStyle("-fx-padding: 0;" + "-fx-border-style: solid inside;"
				        + "-fx-border-width: 1;" + "-fx-border-insets: 0;"
				        + "-fx-border-radius: 0;" + "-fx-border-color: red;");
			} else {
				focus.setStyle("");
				focusDepth = -1;
				focus = null;
			}
		}
		
		public void handle (MouseEvent event) {
			HBox screen = (HBox) pane.getChildren().get(0);
			if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				

				
			} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				
				focus.setTranslateX(event.getSceneX());
				focus.setTranslateY(event.getSceneY());
				
				HBox parent = (HBox)focus.getParent();

				
			} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				
				if(focus != null) {
					

					
					if(focus instanceof HBox) {
						focusHelper(focus, event);
					} else {
						// WE HAVE A TERMINAL NODE
						focus.setStyle("");
						focusDepth = -1;
						focus = null;
					}
					
					
				} else {
					// Find a new focus!
					if(contains(screen,event.getSceneX(),event.getSceneY())) {
						focusDepth = focusDepth+1;
						focus = screen;
						focusHelper(focus, event);
					}
				}
				
			}
		}
	}

	/**
	 * Size of the GUI
	 */
	private static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 250;

	/**
	 * Initial expression shown in the textbox
	 */
	private static final String EXAMPLE_EXPRESSION = "2*x+3*y+4*z+(7+6*z)";

	/**
	 * Parser used for parsing expressions.
	 */
	private final ExpressionParser expressionParser = new SimpleExpressionParser();
	
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
					System.out.println(expression.convertToString(0));
					expressionPane.getChildren().clear();
					expressionPane.getChildren().add(expression.getNode());
					expression.getNode().setLayoutX(WINDOW_WIDTH/4);
					expression.getNode().setLayoutY(WINDOW_HEIGHT/2);

					// If the parsed expression is a CompoundExpression, then register some callbacks
					if (expression instanceof CompoundExpression) {
						((Pane) expression.getNode()).setBorder(Expression.NO_BORDER);
						final MouseEventHandler eventHandler = new MouseEventHandler(expressionPane, (CompoundExpression) expression);
						expressionPane.setOnMousePressed(eventHandler);
						expressionPane.setOnMouseDragged(eventHandler);
						expressionPane.setOnMouseReleased(eventHandler);
					}
				} catch (ExpressionParseException epe) {
					// If we can't parse the expression, then mark it in red
					textField.setStyle("-fx-text-fill: red");
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
