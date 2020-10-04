package pp;

public class ArrayGet extends Expr {
	
	protected Expr array;
	protected Expr i;
	
	public ArrayGet(Expr array, Expr i) {
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
