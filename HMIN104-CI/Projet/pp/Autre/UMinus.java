package pp;

public class UMinus extends Uop {

	public UMinus(Expr e1) {
		this.e1 = e1;
	}
	
	@Override
	public void print() {
		System.out.print(" -");
		e1.print();
	}
}
