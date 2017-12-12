import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

public class Add extends NonTerminal {
	public Add() {
		
	}
	
	public String toString() {
		return "+";
	}
	
	@Override
	public Node getNode() {
		final Label node = new Label("+");
		return node;
	}
}
