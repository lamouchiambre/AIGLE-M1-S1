package pp;

import java.util.ArrayList;

public class Prog {
	AffecVar v;
	ArrayList < Proc > p;
	Inst i;

	public Prog(Inst i) {
        this.v = null;
        this.p = null;
        this.i = i;
	}

	public Prog(AffecVar v, Inst i) {
        this.v = v;
        this.p = null;
        this.i = i;
	}

	public Prog(ArrayList < Proc > p, Inst i) {
        this.v = null;
        this.p = p;
        this.i = i;
	}

	public Prog(AffecVar v, ArrayList < Proc > p, Inst i) {
        this.v = v;
        this.p = p;
        this.i = i;
	}

	public void print() {
        this.v.print();
        String args = "";
        for (int i = 0; i < this.p.size(); i++) {
                args += p.get(i).toString();
        }
        System.out.print(args);
        this.i.print();
	}
}
