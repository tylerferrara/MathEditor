import java.util.ArrayList;
import java.util.LinkedList;

public class NonTerminal implements CompoundExpression {
	
	private CompoundExpression parent;
	private LinkedList<Expression> children;
	
	public NonTerminal() {
		this.children = new LinkedList<Expression>();
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
			Expression child = this.children.get(i);
			if(this.toString().equals(child.toString()))
			{
				// set child's children to have this object as the parent
				LinkedList<Expression> childChildren = ((NonTerminal) child).getSubexpression();
				for(int k = 0; k < childChildren.size(); k++) {
					childChildren.get(k).setParent(this);
					// add child's children to this.children
					addSubexpression(childChildren.get(k));
				}
			
				LinkedList<Expression> children = this.children;
				children.remove(i);
				setSubExpression(children);
				// remove child from this.children
				
			}
			
		}
			{
				
				
				/* this object is the same type as its child*/ {
			}
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
		return str+"\n";
	}

	@Override
	public void addSubexpression(Expression subexpression) {
		this.children.add(subexpression);
	}
	
	public void setSubExpression(LinkedList<Expression> subExpressionList) {
		this.children = subExpressionList;
	}
	
	public LinkedList<Expression> getSubexpression() {
		return this.children;
	}

}
