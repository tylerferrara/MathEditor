import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.Node;

public class Parentheses extends NonTerminal {
	public Parentheses() {
		super.pane.getChildren().add(new Label("()"));
	}
	public String toString() {
		return "()";
	}

}
