
public class DumbTesting {

	public static void main(String[] args) throws ExpressionParseException {
		
		String str = "2+5+6*x+2";
		SimpleExpressionParser parser = new SimpleExpressionParser();
		Expression head = parser.parse(str, false);
		System.out.println("To String!!!");
		System.out.println(head.convertToString(0));
		
	}

}
