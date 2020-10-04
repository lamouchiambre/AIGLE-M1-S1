package pp;

public class AffectArray extends Inst {

	protected Expr e1;
	protected Expr e2;
	protected Expr e3;
	
	public AffectArray(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
	}
	
	public void print() {
        e1.print();
        System.out.print("[");
        e2.print();
        System.out.print("]");
        System.out.print(" := ");
        e3.print();
	}
}
