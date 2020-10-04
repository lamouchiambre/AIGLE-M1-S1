package pp;

public class AffectFun extends Inst {
	
	String n;
	Type t;

	public AffectFun(String n, Type t) {
        this.n = n;
        this.t = t;
	}

	public void print() {
        System.out.print(this.n + ": ");
        this.t.print();
	}
}
