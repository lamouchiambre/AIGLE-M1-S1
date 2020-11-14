package pp;

public class BAnd extends Bop {

	public BAnd(Expr e1, Expr e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public void print() {
		e1.print();
		System.out.print(" and ");
		e2.print();
	}
}
