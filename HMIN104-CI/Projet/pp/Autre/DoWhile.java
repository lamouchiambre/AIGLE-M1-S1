package pp;

public class DoWhile extends Inst {
	
	protected Expr e;
	protected Inst i;
	
	public DoWhile(Expr e, Inst i) {
        this.e = e;
        this.i = i;
}
	
	public void print() {
        System.out.print("While");
        e.print();
        System.out.print("do");
        i.print();
	}
}
