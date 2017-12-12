import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.Node;

public class Parentheses extends NonTerminal {
	public Parentheses() {
		
	}
	public String toString() {
		return "()";
	}
	@Override
	public Node getNode() {
		final Label node = new Label("()");
		return node;
	}
}
