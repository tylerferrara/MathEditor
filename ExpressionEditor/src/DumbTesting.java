
public class DumbTesting {

	public static void main(String[] args) throws ExpressionParseException {
		
		String str = "3*x+2*x";
		SimpleExpressionParser parser = new SimpleExpressionParser();
		Expression head = parser.parse(str, false);
	}

}
