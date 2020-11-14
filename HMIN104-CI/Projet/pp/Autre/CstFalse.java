package pp;

public class CstFalse extends Expr {
	boolean val;
	
	CstFalse () {
		this.val = false;
	}

	@Override
	public void print() {
		System.out.println("false ");
	}
}
