package pp;

public class BDiv extends Bop {
	
	public BDiv(Expr e1, Expr e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public void print() {
		System.out.print(" / ");
	}
}
