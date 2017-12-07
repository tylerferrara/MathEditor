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
		NonTerminal copy;
		if(this.toString().equals("+")) {
			copy = new Add();
		} else if(this.toString().equals("*")) {
			copy = new Multiply();
		} else {
			copy = new Parentheses();
		}
		
		for(int i = 0; i < this.children.size(); i++) {
			copy.addSubexpression((Expression) this.children.get(i).deepCopy());
			copy.getSubexpression().get(i).setParent(copy);
		}
		return copy;
	}

	@Override
	public void flatten() {
		for(int i = 0; i < this.children.size(); i++) {
			Expression child = this.children.get(i);
			if(this.toString().equals(child.toString())) {
				// TIME TO OPTIMIZE
				// set child's children to have this object as the parent
				LinkedList<Expression> childChildren = ((NonTerminal) child).getSubexpression();
				for(int k = childChildren.size()-1; k >= 0; k--) {
					childChildren.get(k).setParent(this);
					// add child's children to this.children
					children.add(i, childChildren.get(k));
				}
			
				LinkedList<Expression> children = this.children;		
				children.remove(i+childChildren.size());
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
		return recursiveConvertToString(indentLevel)+"\n";
	}
	
	@Override
	public String recursiveConvertToString(int indentLevel) {
		String str = "";
		for(int i = 0; i < indentLevel; i++) {
			str += "\t";
		}
		str += this.toString();
		for(int i = 0; i < this.children.size(); i++) {
			str += "\n";
			str += this.children.get(i).recursiveConvertToString(indentLevel+1);
		}
		return str;
		
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
