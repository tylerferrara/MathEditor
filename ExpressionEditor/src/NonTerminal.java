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
		//
		//
		//  NEED TO IMPLIMENT THIS CRAP
		//
		return null;
	}

	@Override
	public void flatten() {
		
		for(int i = 0; i < this.children.size(); i++) {
			String parent = this.toString();
			String child = this.children.get(i).toString();
			if(parent.equals(child))
			{
				setSubExpression(this.children.get(i));
				
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
		return str;
	}

	@Override
	public void addSubexpression(Expression subexpression) {
		this.children.add(subexpression);
	}
	public void setSubExpression(Expression subexpression)
	{
		for(int i =0; i< this.children.size();i++)
		{
			this.children.set(i, subexpression);
		}
	}
	
	public ArrayList<Expression> getSubexpression() {
		return this.children;
	}

}
