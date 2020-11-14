package pp;

public class CstInt extends Cst {
	
	protected String n;
	
	CstInt(String n) {
		this.n = n;
	}
	
	@Override
	public void print() {
		System.out.print(this.n + " ");
	}
}
