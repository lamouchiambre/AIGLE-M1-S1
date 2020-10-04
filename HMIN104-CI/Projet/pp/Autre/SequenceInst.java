package pp;

public class SequenceInst extends Inst {
	
	protected Inst i;
	protected Inst i2;
	
	public SequenceInst(Inst i, Inst i2) {
        this.i = i;
        this.i2 = i2;
	}
	
	public void print() {
        this.i.print();
        System.out.print(";");
        this.i2.print();
	}
}
