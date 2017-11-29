
public class Terminal implements Expression {

	private CompoundExpression parent;
	private int value;
	private String name;
	
	public Terminal(int value) {
		this.value = value;
	}
	
	public Terminal(String name) {
		this.name = name;
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
		return this;
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

}
