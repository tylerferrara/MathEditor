import javafx.scene.Node;
import javafx.scene.control.Label;

public class Multiply extends NonTerminal {
	public Multiply() {
		
	}
	public String toString() {
		return "*";
	}
	@Override
	public Node getNode() {
		final Label node = new Label("*");
		return node;
	}
}
