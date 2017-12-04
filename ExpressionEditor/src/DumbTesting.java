
public class DumbTesting {

	public static void main(String[] args) throws ExpressionParseException {
		
		String str = "((3-4*x)+6*3)+3+(8*5*x)";
		SimpleExpressionParser parser = new SimpleExpressionParser();
		Expression head = parser.parse(str, false);
		System.out.println("To String!!!");
		System.out.println(head.convertToString(0));
		
	}

}
