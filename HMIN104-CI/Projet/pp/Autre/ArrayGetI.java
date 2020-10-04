package pp;

public class ArrayGetI extends Inst {
	
	protected Expr array;
	protected Expr i;
	
	public ArrayGetI(Expr array, Expr i) {
		this.array = array;
		this.i = i;
	}
	
	public void print() {
		array.print();
		System.out.print("[");
		i.print();
		System.out.print("]");
	}
}
