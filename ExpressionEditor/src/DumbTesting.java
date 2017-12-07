import java.util.ArrayList;

public class DumbTesting {

	public static void main(String[] args) throws ExpressionParseException {
		
//		String str = "4*(z+5*x)";
//		SimpleExpressionParser parser = new SimpleExpressionParser();
//		Expression head = parser.parse(str, false);
//		System.out.println("To String!!!");
//		System.out.println(head.convertToString(0));
		
		ArrayList<Integer> lst = new ArrayList<Integer>();
		lst.add(1);
		lst.add(8);
		lst.add(22);
		lst.add(22);
		if((lst.size()/2.0) % 2 == 0) {
			System.out.println((lst.size()/2)-1);
		} else {
			System.out.println((lst.size()/2));
		}
		
	}

}
