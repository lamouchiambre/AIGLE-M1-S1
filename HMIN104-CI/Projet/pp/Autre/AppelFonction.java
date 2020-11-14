package pp;

import java.util.ArrayList;

public class AppelFonction extends Expr {

	protected App a;
	protected ArrayList < Expr > args;

	public AppelFonction(App a) {
		this.a = a;
		this.args = null;
	}

	public AppelFonction(App a, ArrayList < Expr > args) {
		this.a = a;
		this.args = args;
	}
	public void print() {
		String s = "";
		for (Expr e: args)
			s += e.toString().toString();
		this.a.print();
		System.out.print("(" + s + ")");
	}
}
