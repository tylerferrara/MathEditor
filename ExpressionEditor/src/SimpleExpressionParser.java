import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleExpressionParser implements ExpressionParser {
	/*
	 * Attempts to create an expression tree -- flattened as much as possible -- from the specified String.
         * Throws a ExpressionParseException if the specified string cannot be parsed.
	 * @param str the string to parse into an expression tree
	 * @param withJavaFXControls you can just ignore this variable for R1
	 * @return the Expression object representing the parsed expression tree
	 */
	public Expression parse (String str, boolean withJavaFXControls) throws ExpressionParseException {
		// Remove spaces -- this simplifies the parsing logic
		str = str.replaceAll(" ", "");
		Expression expression = parseExpression(str);
		if (expression == null) {
			// If we couldn't parse the string, then raise an error
			throw new ExpressionParseException("Cannot parse expression: " + str);
		}

		// Flatten the expression before returning
		expression.flatten();
		return expression;
	}
	
	protected Expression parseExpression (String str) {
		
		//           This code needs to be somewhere else, 
		////             NOT in recursive function
		String alphabet = "qwertyuiopasdfghjklzxcvbnm";
		ArrayList<Character> alphabetList = new ArrayList<Character>();
		for(char c: alphabet.toCharArray()) {
			alphabetList.add(c);
		}
		////
		//


/**
 * Starter code to implement an ExpressionParser. Your parser methods should use the following grammar:
 * E := A | X
 * A := A+M | M
 * M := M*M | X
 * X := (E) | L
 * L := [0-9]+ | [a-z]
 */
		
		if(str.indexOf('+') > -1) { // A
			// create node
			Add plus = new Add();
			// addSubexpression(left side of plus 'using recursion');
			String left = str.substring(0, str.indexOf('+'));
			plus.addSubexpression(parseExpression(left));
			// do the same for right side of recursion
			String right = str.substring(str.indexOf('+'));
			plus.addSubexpression(parseExpression(right));
			return plus;
		} else if(str.indexOf('*') > -1) { // M
			// create node
			Multiply times = new Multiply();
			// addSubexpression(left side of plus 'using recursion');
			String left = str.substring(0, str.indexOf('*'));
			times.addSubexpression(parseExpression(left));
			// do the same for right side of recursion
			String right = str.substring(str.indexOf('*'));
			times.addSubexpression(parseExpression(right));
			return times;
		} else if(str.indexOf('(') > -1) { // X
			String inside = str.substring(str.indexOf('(')+1,str.indexOf(')'));
			Parentheses paren = new Parentheses();
			paren.addSubexpression(parseExpression(inside));
			return paren;
		} else { // L
			Terminal terminal = new Terminal();
			return terminal;
		}
	}
	
}
