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
		final NonTerminal copy = new NonTerminal();
		for (Expression child : children) {
			copy.children.add((Expression) child.deepCopy());
			child.setParent(this);
		}
		return copy;
	}

	@Override
	public void flatten() {
		
		for(int i = 0; i < this.children.size(); i++) {
//			if(/* this object is the same type as its child*/) {
//				
//					set child's children to have this object as the parent
//					add child's children to this.children
//					remove child from this.children
//				
//			
//				if( child is not of type Terminal ) {
//					this.children.get(i).flatten();
//				}
//			}
			

		}
		
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
