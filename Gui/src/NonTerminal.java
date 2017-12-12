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

public class NonTerminal implements CompoundExpression {
	
	private CompoundExpression parent;
	private LinkedList<Expression> children;
	private HBox pane;
	private int depth;
	
	/**
	 * @return NonTerminal
	 * public constructor for NonTerminal
	 */
	public NonTerminal() {
		this.children = new LinkedList<Expression>();
	}
	public boolean contains(double x, double y) {
		return pane.contains(x, y);	
		}
	/**
	 * @return CompoundExpression
	 * returns the parent of the NonTerminal
	 */
	@Override
	public CompoundExpression getParent() {
		return this.parent;
	}

	/**
	 * @param parent is CompoundExpression
	 * sets the parent of the NonTerminal to parent
	 */
	@Override
	public void setParent(CompoundExpression parent) {
		this.parent = parent;
	}

	/**
	 * @return Expression
	 * creates a copy of the entire Expression with the NonTerminal at its root
	 */
	@Override
	public Expression deepCopy() {
		NonTerminal copy;
		if(this.toString().equals("+")) {
			copy = new Add();
		} else if(this.toString().equals("*")) {
			copy = new Multiply();
		} else {
			copy = new Parentheses();
		}
		
		for(int i = 0; i < this.children.size(); i++) {
			copy.addSubexpression((Expression) this.children.get(i).deepCopy());
			copy.getSubexpression().get(i).setParent(copy);
		}
		return copy;
	}

	/**
	 * if one of the children of the NonTerminal is the same type
	 * it will make the child's children the children of the
	 * NonTerminal and set the NonTerminal as their parent,
	 * then removes the first child from the NonTerminal's children
	 */
	@Override
	public void flatten() {
		for(int i = 0; i < this.children.size(); i++) {
			Expression child = this.children.get(i);
			child.flatten();
			if(this.toString().equals(child.toString())) {
				// TIME TO OPTIMIZE
				// set child's children to have this object as the parent
				LinkedList<Expression> childChildren = ((NonTerminal) child).getSubexpression();
				for(int k = childChildren.size()-1; k >= 0; k--) {
					childChildren.get(k).setParent(this);
					// add child's children to this.children
					children.add(i, childChildren.get(k));
				}
			
				LinkedList<Expression> children = this.children;		
				children.remove(i+childChildren.size());
				setSubExpression(children);
				// remove child from this.children
				
			}
			
		}
		
	}

	/**
	 * @param indentLevel number to be passed to recursiveConvertToString
	 * @return String
	 * returns the result of recursiveConvertToString
	 * called with the given indentLevel with a new line at the end
	 */
	@Override
	public String convertToString(int indentLevel) {
		return recursiveConvertToString(indentLevel)+"\n";
	}
	
	/**
	 * @param indentLevel number of tabs to be added
	 * @return String
	 * indents the given indentLevel number of tabs then adds the
	 * toString value of the NonTerminal and then calls
	 * itself on the children with the original indentLevel + 1
	 */
	@Override
	public String recursiveConvertToString(int indentLevel) {
		String str = "";
		for(int i = 0; i < indentLevel; i++) {
			str += "\t";
		}
		str += this.toString();
		for(int i = 0; i < this.children.size(); i++) {
			str += "\n";
			str += this.children.get(i).recursiveConvertToString(indentLevel+1);
		}
		return str;
		
	}

	/**
	 * @param subexpression
	 * adds the given Expression to the NonTerminal's children
	 */
	@Override
	public void addSubexpression(Expression subexpression) {
		this.children.add(subexpression);
	}
	
	/**
	 * @param subExpressionList
	 * sets the NonTerminal's children to subExpressionList
	 */
	public void setSubExpression(LinkedList<Expression> subExpressionList) {
		this.children = subExpressionList;
	}
	
	/**
	 * @return the NonTerminal's children
	 */
	public LinkedList<Expression> getSubexpression() {
		return this.children;
	}
	public Expression getMostSpecificFocus(double x, double y) {
		if(this.pane.contains(x, y)) {
			return this;
		}
		else {
			return null;
			}
	}
	@Override
	public Node getNode() {
		HBox hbox = new HBox();
		for(int i = 0; i < this.children.size(); i++) {
			hbox.getChildren().add(this.children.get(i).getNode());
			if(i+1 != this.children.size()) {
				hbox.getChildren().add(new Label(this.toString()));
			}
		}
		return hbox;
	}

}
