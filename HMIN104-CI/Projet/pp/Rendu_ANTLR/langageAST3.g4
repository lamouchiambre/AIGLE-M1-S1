grammar langageAST3;


listexpr returns [ArrayList<Expr> lExp]
        @init { $lExp=new ArrayList<Expr>(); } :
        (e=expr { $lExp.add($e.e); } )+ ;


listDec returns [ArrayList<Dec> lDec]
	@init { $lDec = new ArrayList<Dec>(); } : 
	(e=dec { $lDec.add($e.decc); })+ ;

//Abstract
dec returns [Dec decc] :
     decVar | decFonc ;

listeDecVar returns [ArrayList<DecVar> lDecVar]
	    @init { $lDecVar = new ArrayList<DecVar>(); } : 
	    (e=decVar { $lDecVar.add($e.decVa); } )+ ;

listeDecFonc returns [ArrayList<DecFonc> lDecFo]
	     @init { $lDecFo = new ArrayList<DecFonc>(); } : 
	     (e=decFonc { $lDecFo.add($e.decFo); } )+ ;

decVar returns [DecVar decVa] : 
       'var' iden=Identif ':' ty=type { $decVa = new DecVar($iden.text, $ty.t); } ;
       
decPar returns [DecPar decPa] :
       iden=Identif ':' ty=type { $decPa = new DecPar($iden.text, $ty.t);}
       | expr ;
       
prog returns [Prog pr] :
     lDec=listDec inst=instr { $pr = new Prog($lDec.lDec, $inst.i); }
     | inst=instr { $pr = new Prog($inst.i); }
     | lDec=listDec { $pr = new Prog($lDec.lDec); } ;

decFonc returns [DecFonc decFo] : 
	iden=Identif '(' lDecPa=listDecPar ')' ':' ty=type inst=instr
	{ $decFo = new DecFonc($iden.text, $lDecPa.lDecPar, $ty.t, $inst.i); }
	| iden=Identif '()' ':' ty=type inst=instr
	{ $decFo = new DecFonc($iden.text, $ty.t, $inst.i); }
	| iden=Identif '(' lDecPa=listDecPar ')' ':' ty=type lDecVa=listeDecVar inst=instr
	{ $decFo = new DecFonc($iden.text, $lDecPa.lDecPar, $ty.t, $lDecVa.lDecVar, $inst.i); }
	| iden=Identif '()' ':' ty=type lDecVa=listeDecVar inst=instr
	{ $decFo = new DecFonc($iden.text, $ty.t, $lDecVa.lDecVar, $inst.i); } ;

listDecPar returns [ArrayList<DecPar> lDecPar]
	   @init { $lDecPar = new ArrayList<DecPar>(); } : 
	   (e=decPar { $lDecPar.add($e.decPa); } )+ ;



//Abstarct
instr returns [Inst i] :
     iden =Identif ':=' e1=expr { $i = new Affec($iden.text, $e1.e); }
     | 'if' e1=expr 'then' i1=instr 'else' i2=instr
     { $i = new IfThenElse($e1.e, $i1.i, $i2.i); }
     | fun
     | e1=expr '[' e2=expr ']' ':=' e3=expr { $i = new AffecArray($e1.e, $e2.e, $e3.e); }
     | 'while' e1=expr 'do' i1=instr { $i = new DoWhile($e1.e, $i1.i); }
     | 'skip' { $i = new Skip(); }
     | i1=instr ';' i2=instr { $i = new SequenceInst($i1.i, $i2.i); } ;


	
//Abstarct
expr returns [Expr e] :
     fun
     | e1=expr '[' e2=expr ']' { $e = new ArrayGet($e1.e, $e2.e); }
     | n=Number { $e = new CstInt($n.text); }
     | 'false' { $e = new CstFalse(); }
     | 'true' { $e = new CstTrue(); }
     | Identif
     | e1=expr '+' e2=expr { $e = new BAdd($e1.e, $e2.e); }
     | e1=expr '-' e2=expr { $e = new BSub($e1.e, $e2.e); }
     | e1=expr '*' e2=expr { $e = new BMul($e1.e, $e2.e); }
     | e1=expr '/' e2=expr { $e = new BDiv($e1.e, $e2.e); }
     | e1=expr 'and' e2=expr { $e = new BAnd($e1.e, $e2.e); }
     | e1=expr 'or' e2=expr { $e = new BOr($e1.e, $e2.e); }
     | e1=expr '<' e2=expr { $e = new BInf($e1.e, $e2.e); }
     | e1=expr '<=' e2=expr { $e = new BInfEg($e1.e, $e2.e); }
     | e1=expr '>' e2=expr { $e = new BSup($e1.e, $e2.e); }
     | e1=expr '>=' e2=expr { $e = new BSupEg($e1.e, $e2.e); }
     | e1=expr '=' e2=expr { $e = new BEg($e1.e, $e2.e); }
     | e1=expr '!=' e2=expr { $e = new BInfEg($e1.e, $e2.e); }
     | '-' e1=expr { $e = new UMinus($e1.e); }
     | 'not' e1=expr { $e = new UNot($e1.e); }
     | 'new array of ' ty=type '[' e1=expr ']' { $e = new NewArray($ty.t, $e1.e); } ;

fun returns [AppelFonction f] :
    a=app '(' lExp=listexpr')' { $f = new AppelFonction($a.a, $lExp.lExp); }
    | a=app '(' ')' { $f = new AppelFonction($a.a); };
    
app returns [App a] :
    'read' { $a = new Read(); }
    | 'write' { $a = new Write(); }
    | iden=Identif { $a = new Fun($iden.text); };


type returns [Type t] :
     'integer' {$t = new TypeInt();}
     | 'boolean' {$t = new TypeBool();}
     | 'array of type ' ty=type {$t = new TypeArray($ty.t);};

Number : [0-9]+;

Identif : ([a-z]+ | [A-Z]+);

WS : [ \t\r\n]+ -> skip ;
