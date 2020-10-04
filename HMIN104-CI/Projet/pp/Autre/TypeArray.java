package pp;

public class TypeArray extends Type {
	
	protected Type t;
	
	public TypeArray(Type t) {
        this.t = t;
	}
	
	public void print() {
        System.out.print("array of ");
        t.print();
	}
}
