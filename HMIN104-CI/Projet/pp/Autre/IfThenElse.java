package pp;

public class IfThenElse extends Inst {
	
	protected Expr e;
	protected Inst i;
	protected Inst i2;
	
	public IfThenElse(Expr e, Inst i, Inst i2) {
        this.e = e;
        this.i = i;
        this.i2 = i2;
	}
	
	public void print() {
        System.out.print("If ");
        e.print();
        System.out.print("Then");
        i.print();
        System.out.print("Else");
        i2.print();
	}
}
