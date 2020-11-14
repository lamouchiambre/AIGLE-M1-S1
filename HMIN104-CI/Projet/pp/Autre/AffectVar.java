package pp;

import java.util.ArrayList;

public class AffectVar extends Inst {
	
	ArrayList < AffectFun > af;

	public AffectVar(ArrayList < AffectFun > af) {
        this.af = af;
	}

	public void print(){};
}
