package pp;

public abstract class Uop extends Expr {

	protected Expr e1;
	
	@Override
	public abstract void print();
}
