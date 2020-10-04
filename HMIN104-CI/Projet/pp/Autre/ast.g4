grammar ast;



listexpr returns [ArrayList<Expr> l_exp]
		@init{$l_exp=new ArrayList<Expr>();}
		: (e = expr {$l_exp.add($e.e);})+ ;

listaffec returns [ArrayList<AffecFun> l_affec]
		@init{$l_affec=new ArrayList<AffecFun>();}
		: ( a = affec {$l_affec.add($a.la);})+ ;


listaffecvar returns [ArrayList<AffecVar> lva]
		@init{$lva=new ArrayList<AffecVar>();}
		: ( a = affecvar {$lva.add($a.va);})+ ;

listf returns [ArrayList<Proc> lp]
		@init{$lp=new ArrayList<Proc>();}
		: ( a = proc {$lp.add($a.prc);})+ ;


prog returns [Prog pr]
	: lv = affecvar linst = listf i = inst { $pr = new Prog($lv.va, $linst.lp, $i.i);}
	| linst = listf i = inst { $pr = new Prog($linst.lp, $i.i);}
	| lv = affecvar i = inst {$pr = new Prog($lv.va, $i.i);}
	| i = inst {$pr=new Prog($i.i);};

proc returns [Proc prc]
	: v=Var'(' la=listaffec ')'  ':' t=type
	  af=listaffecvar i=inst {$prc = new Proc($v.text, $la.l_affec, $t.t, $af.lva, $i.i);}
	| v=Var'(' ')'  ':' t=type
	  af=listaffecvar i=inst {$prc = new Proc($v.text	, $t.t, $af.lva, $i.i);} // no args
	| v=Var'(' la=listaffec ')'
	  af=listaffecvar i=inst {$prc = new Proc($v.text, $la.l_affec, $af.lva, $i.i);} // no Type
	| v=Var'(' ')'
	  af=listaffecvar i=inst {$prc = new Proc($v.text, $af.lva, $i.i);} //no type and no args
	| v=Var'(' la=listaffec ')'  ':' t=type
	  i=inst {$prc = new Proc($v.text, $la.l_affec, $t.t, $i.i);} //no vars
	| v=Var'(' la=listaffec ')'
		  i=inst {$prc = new Proc($v.text, $i.i, $la.l_affec);} //no vars and no type
	| v=Var'(' ')'
	  i=inst {$prc = new Proc($v.text, $i.i);} // no vars no args no type
	| v=Var '(' ')' ':' t=type
		i=inst {$prc = new Proc($v.text, $t.t, $i.i);}; //no vars no arg

inst returns [Inst i]
	: v=Var ':=' e1=expr {$i = new Affec($v.text, $e1.e);}
	| 'if' e1=expr 'then' i1=inst 'else' i2=inst {$i = new IfThenElse($e1.e, $i1.i, $i2.i);}
	| fun
	| e1=expr '[' e2=expr ']' ':=' e3=expr {$i = new AffecArray($e1.e, $e2.e, $e3.e);}
	| 'while' e1=expr 'do' i1=inst {$i = new DoWhile($e1.e, $i1.i);}
	| 'skip' {$i = new Skip();}
	| i1=inst ';' i2=inst {$i = new SequenceInst($i1.i, $i2.i);};

affec returns [AffecFun la]
	 : '(' v=Var ':' t=type ')' {$la = new AffecFun($v.text, $t.t);}
	 | v=Var ':' t=type {$la = new AffecFun($v.text, $t.t);};

affecvar returns [AffecVar va]
	 : 'var' al = listaffec {$va = new AffecVar($al.l_affec);};

expr returns [Expr e]
	: fun
	| e1=expr '[' e2=expr ']' {$e = new ArrayGet($e1.e, $e2.e);}
	| n=Number {$e = new CstInt($n.text );}
	| 'false' {$e = new CstFalse();}
	| 'true' {$e = new CstTrue();}
	| Var
	| e1=expr '+' e2=expr {$e = new BAdd($e1.e, $e2.e);}
	| e1=expr '-' e2=expr {$e = new BSub($e1.e, $e2.e);}
	| e1=expr '*' e2=expr {$e = new BMul($e1.e, $e2.e);}
	| e1=expr '/' e2=expr {$e = new BDiv($e1.e, $e2.e);}
	| e1=expr 'and' e2=expr {$e = new BAnd($e1.e, $e2.e);}
	| e1=expr 'or' e2=expr {$e = new BOr($e1.e, $e2.e);}
	| e1=expr '<' e2=expr {$e = new BInf($e1.e, $e2.e);}
	| e1=expr '<=' e2=expr {$e = new BInfEg($e1.e, $e2.e);}
	| e1=expr '>' e2=expr {$e = new BSup($e1.e, $e2.e);}
	| e1=expr '>=' e2=expr {$e = new BSupEg($e1.e, $e2.e);}
	| e1=expr '=' e2=expr {$e = new BEg($e1.e, $e2.e);}
	| e1=expr '!=' e2=expr {$e = new BInfEg($e1.e, $e2.e);}
	| '-' e1=expr {$e = new UMinus($e1.e);}
	| 'not' e1=expr {$e = new UNot($e1.e);}
	| 'new array of ' t1=type '[' e1=expr ']' {$e = new NewArray($t1.t, $e1.e);};

fun returns [AppelFonction f]
	: a=app '('le=listexpr')' {$f = new AppelFonction($a.a,$le.l_exp);}
	| a=app '(' ')' {$f = new AppelFonction($a.a);};
	
app returns [App a]
	: 'read'  {$a = new Read();}
	| 'write' {$a = new Write();}
	|  v=Var    {$a = new Fun($v.text);};


type returns [Type t]
	 : 'integer' {$t = new TypeInt();}
	 | 'boolean' {$t = new TypeBool();}
	 | 'array of type ' t1=type {$t = new TypeArray($t1.t);};


Number : [0-9]+;

Var : ([a-z]+ | [A-Z]+);
WS : [ \t\r\n]+ -> skip;
