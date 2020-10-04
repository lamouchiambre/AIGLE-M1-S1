package pp;

public class Affect extends Inst {
	
	protected String v;
	protected Expr e;
	
	public Affect(String v, Expr e) {
        this.v = v;
        this.e = e;
	}

	public void print() {
        System.out.print(this.v);
        System.out.print(" := ");
        e.print();
	}
}
