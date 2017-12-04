import java.util.ArrayList;

public class NonTerminal implements CompoundExpression {
	
	private CompoundExpression parent;
	private ArrayList<Expression> children;
	
	public NonTerminal() {
		this.children = new ArrayList<Expression>();
	}
	
	@Override
	public CompoundExpression getParent() {
		return this.parent;
	}

	@Override
	public void setParent(CompoundExpression parent) {
		this.parent = parent;
	}

	@Override
	public Expression deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flatten() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String convertToString(int indentLevel) {
		String str = "";
		for(int i = 0; i < indentLevel; i++) {
			str += "\t";
		}
		str += this.toString();
		for(int i = 0; i < this.children.size(); i++) {
			str += "\n";
			str += this.children.get(i).convertToString(indentLevel+1);
		}
		return str;
	}

	@Override
	public void addSubexpression(Expression subexpression) {
		this.children.add(subexpression);
	}
	
	public ArrayList<Expression> getSubexpression() {
		return this.children;
	}

}
