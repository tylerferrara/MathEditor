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

/**
 * Starter code to implement an ExpressionParser. Your parser methods should use the following grammar:
 * E := A | X
 * A := A+M | M
 * M := M*M | X
 * X := (E) | L
 * L := [0-9]+ | [a-z]
 */
		
		if(findOuterMostChar(str, '+') > -1) { // A
			int plusIndex = findOuterMostChar(str, '+');
			// create node
			Add plus = new Add();
			// addSubexpression(left side of plus 'using recursion');
			String left = str.substring(0, plusIndex);
			plus.addSubexpression(parseExpression(left));
			// do the same for right side of recursion
			String right = str.substring(plusIndex+1);
			plus.addSubexpression(parseExpression(right));
			// set current node as parent for subexpressions
			for(Expression e: plus.getSubexpression()) {
				e.setParent(plus);
			}
			return plus;
		} else if(findOuterMostChar(str, '*') > -1) { // M
			int timesIndex = findOuterMostChar(str, '*');
			// create node
			Multiply times = new Multiply();
			// addSubexpression(left side of plus 'using recursion');
			String left = str.substring(0, timesIndex);
			times.addSubexpression(parseExpression(left));
			// do the same for right side of recursion
			String right = str.substring(timesIndex+1);
			times.addSubexpression(parseExpression(right));
			// set current node as parent for subexpressions
			for(Expression e: times.getSubexpression()) {
				e.setParent(times);
			}
			return times;
		}  else if(str.indexOf('(') > -1) { // X
			String inside = str.substring(1,str.length()-1);
			Parentheses paren = new Parentheses();
			paren.addSubexpression(parseExpression(inside));
			// set current node as parent for subexpressions
			for(Expression e: paren.getSubexpression()) {
				e.setParent(paren);
			}
			return paren;
		} else { // L
			Terminal terminal = new Terminal(str);
			return terminal;
		}
	}
	
	/**
	 * Will return the index of the last character inside the given string
	 * as long as that character is not inside a set of parentheses.
	 * @param str
	 * @param c
	 * @return
	 */
	private int findOuterMostChar(String str, char c) {
		int index = -1;
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int numOfOpenParens = 0;
		for(int i = 0; i < str.length(); i++) {
			char testChar = str.charAt(i);
			if(testChar == '(') {
				numOfOpenParens++;
			} else if (testChar == ')') {
				numOfOpenParens--;
			} else if (testChar == c && numOfOpenParens == 0) {
				indexes.add(i);
			}
		}
		if(indexes.size() > 0) {
			return findMedium(indexes);
		} else {
			return -1;
		}
	}
	
	private int findMedium(ArrayList<Integer> lst) {
		int index = 0;
		if((lst.size()/2.0) % 2 == 0) {
			index = ((lst.size()/2)-1);
		} else {
			index = (lst.size()/2);
		}
		return lst.get(index);
	}
}
