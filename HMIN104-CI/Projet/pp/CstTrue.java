package pp;

public class CstTrue extends Cst {
	boolean val;
	
	CstTrue () {
		this.val = true;
	}
	
	@Override
	public void print() {
		System.out.print("true ");
	}
}
