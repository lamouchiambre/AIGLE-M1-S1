package pp;

import java.util.ArrayList;

public class Proc {

	String n;
	ArrayList < AffecFun > funs;
	Type t;
	ArrayList < AffecVar > vars;
	Inst i;

	//all
	public Proc(String n, ArrayList < AffecFun > funs, Type t, ArrayList < AffecVar > vars, Inst i) {
		this.n = n;
		this.funs = funs;
		this.t = t;
		this.vars = vars;
		this.i = i;
	}

	//no args
	public Proc(String n, Type t, ArrayList < AffecVar > vars, Inst i) {
		this.n = n;
		this.funs = null;
		this.t = t;
		this.vars = vars;
		this.i = i;
	}

	//no type
	public Proc(String n, ArrayList < AffecFun > funs, ArrayList < AffecVar > vars, Inst i) {
		this.n = n;
		this.funs = funs;
		this.t = null;
		this.vars = vars;
		this.i = i;
	}

	//no types no args
	public Proc(String n, ArrayList < AffecVar > vars, Inst i) {
		this.n = n;
		this.funs = null;
		this.t = null;
		this.vars = vars;
		this.i = i;
	}

	//no vars
	public Proc(String n, ArrayList < AffecFun > funs, Type t, Inst i) {
		this.n = n;
		this.funs = funs;
		this.t = t;
		this.vars = null;
		this.i = i;
	}

	//no vars no args
	public Proc(String n, Type t, Inst i) {
		this.n = n;
		this.funs = null;
		this.t = t;
		this.vars = null;
		this.i = i;
	}

	//no vars no type
	public Proc(String n, Inst i, ArrayList < AffecFun > funs) {
		this.n = n;
		this.funs = funs;
		this.t = null;
		this.vars = null;
		this.i = i;
	}

	//no vars no type no args
	public Proc(String n, Inst i) {
		this.n = n;
		this.funs = null;
		this.t = null;
		this.vars = null;
		this.i = i;
	}
}
