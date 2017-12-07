
public class Terminal implements Expression {

	private CompoundExpression parent;
	private String value;
	
	public Terminal() {
	}
	
	public Terminal(String value) {
		this.value = value;
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
		return recursiveConvertToString(indentLevel);
	}
	
	@Override
	public String recursiveConvertToString(int indentLevel) {
		String str = "";
		for(int i = 0; i < indentLevel; i++) {
			str += "\t";
		}
		str += this.value;
		return str;
	}

}
