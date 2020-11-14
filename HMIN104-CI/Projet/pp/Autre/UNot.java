package pp;

public class UNot extends Uop {

	public UNot(Expr e1) {
		this.e1 = e1;
	}
	
	@Override
	public void print() {
		System.out.print(" not ");
		e1.print();
	}
}
