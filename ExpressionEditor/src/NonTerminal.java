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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSubexpression(Expression subexpression) {
		this.children.add(subexpression);
	}
	
	public ArrayList<Expression> getSubexpression() {
		return this.children;
	}

}
