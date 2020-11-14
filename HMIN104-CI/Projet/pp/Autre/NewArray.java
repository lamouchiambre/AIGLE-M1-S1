package pp;

public class NewArray extends Expr {
	
	protected Type t;
	protected Expr s;
	
	public NewArray(Type t, Expr s) {
		this.t = t;
		this.s = s;
	}
	
	public void print() {
		System.out.print("New array of ");
		t.print();
		System.out.print("[");
		s.print();
		System.out.print("]");
	}
}
