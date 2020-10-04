package pp;

public class BSup extends Bop {

	public BSup(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
	}
	
	public void print() {
        e1.print();
        System.out.print(" > ");
        e2.print();
	}
}
