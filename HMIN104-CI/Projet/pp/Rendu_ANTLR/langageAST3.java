import java.util.ArrayList;
import org.antlr.v4.runtime .*;

class Prog {
    ArrayList < Dec > p;
    Inst i;

    public Prog(Inst i) {
        System.out.println("1");
        this.p = null;
        this.i = i;
    }
    
    public Prog(ArrayList < Dec > p) {
        System.out.println("2");
        this.p = p;
        this.i = null;
    }

    public Prog(ArrayList < Dec > p, Inst i) {
        System.out.println("3");
        this.p = p;
        this.i = i;
    }

    public void print() {
        
        for (int i = 0; i < this.p.size(); i++){ 
    
	        p.get(i).print();
        } 
        
        this.i.print();
    }
}

abstract class Dec {

    public abstract void print();
}

class DecVar extends Dec {
    String iden;
    Type t;

    public DecVar(String iden, Type t) {
	this.iden = iden;
	this.t = t;
    }
    public void print(){
	System.out.print("var ");
	iden.toString();
	System.out.print(" : ");
	t.print();
    }
}

class DecPar extends Dec {
    String iden;
    Type t;

    public DecPar(String iden, Type t) {
	this.iden = iden;
	this.t = t;
    }
    public void print(){
	iden.toString();
	System.out.print(" : ");
	t.print();
    }
}

class DecFonc extends Dec {
    String iden;
    ArrayList<DecPar> lDecPar;
    ArrayList<DecVar> lDecVar;
    Type t;
    Inst i;

    public DecFonc(String iden, ArrayList<DecPar> lDecPar, Type t, Inst i) {
	this.iden = iden;
	this.lDecPar = lDecPar;
	this.lDecVar = null;
	this.t = t;
	this.i = i;
    }
    
    public DecFonc(String iden,  Type t, Inst i) {
	this.iden = iden;
	this.lDecPar = null;
	this.lDecVar = null;
	this.t = t;
	this.i = i;
    }

    public DecFonc(String iden, ArrayList<DecPar> lDecPar, Type t, ArrayList<DecVar> lDecVar, Inst i) {
	this.iden = iden;
	this.lDecPar = lDecPar;
	this.t = t;
	this.lDecVar = lDecVar;
	this.i = i;
    }

    public DecFonc(String iden, Type t, ArrayList<DecVar> lDecVar, Inst i) {
	this.iden = iden;
	this.t = t;
	this.lDecVar = lDecVar;
	this.i = i;
	this.lDecVar = null;
    }

    public void print(){
        String args = "";
        String var = "";
        iden.toString();
        if(lDecPar != null){
            for (int i = 0; i < this.lDecPar.size(); i++)
                args += lDecPar.get(i).toString();
                System.out.print("(" + args+ ") : ");
        }else System.out.print("() : ");
        t.print();
        if(lDecVar != null){
            for (int i = 0; i < this.lDecVar.size(); i++)
                var += lDecPar.get(i).toString();
                System.out.print(var);
        }
        i.print();
        
    }
}

abstract class Type {
    abstract void print();
}
class TypeInt extends Type {
    public void print() {
        System.out.print("integer ");
    }
}
class TypeBool extends Type {
    public void print() {
        System.out.print("boolean ");
    }
}
class TypeArray extends Type {
    protected Type t;
    public TypeArray(Type t) {
        this.t = t;
    }
    public void print() {
        System.out.print("array of ");
        t.print();
    }
}

abstract class Inst {
    abstract void print();
}
class Affec extends Inst {
    protected String iden;
    protected Expr e;
    
    public Affec(String iden, Expr e) {
        this.iden = iden;
        this.e = e;
    }
    public void print() {
        iden.toString();
        System.out.print(" := ");
        e.print();
    }
}

class AffecArray extends Inst {
    protected Expr e1;
    protected Expr e2;
    protected Expr e3;
    public AffecArray(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }
    public void print() {
        e1.print();
        System.out.print("[");
        e2.print();
        System.out.print("]");
        System.out.print(" := ");
        e3.print();
    }
}

class IfThenElse extends Inst {
    protected Expr e;
    protected Inst i;
    protected Inst i2;
    public IfThenElse(Expr e, Inst i, Inst i2) {
        this.e = e;
        this.i = i;
        this.i2 = i2;
    }
    public void print() {
        System.out.print("If ");
        e.print();
        System.out.print("Then");
        i.print();
        System.out.print("Else");
        i2.print();
    }
}
class DoWhile extends Inst {
    protected Expr e;
    protected Inst i;
    public DoWhile(Expr e, Inst i) {
        this.e = e;
        this.i = i;
    }
    public void print() {
        System.out.print("While");
        e.print();
        System.out.print("do");
        i.print();
    }
}
class Skip extends Inst {
    public void print() {
        System.out.print("skip");
    }
}

class SequenceInst extends Inst {
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

abstract class Expr {
    public abstract void print();
}
abstract class Cst extends Expr {
    public abstract void print();
}

class CstInt extends Cst {
    protected String name;
    public CstInt(String n) {
        this.name = n;
    }
    public void print() {
        System.out.print(this.name + " ");
    }
}
class CstTrue extends Cst {
    public void print() {
        System.out.print("true ");
    }
}
class CstFalse extends Cst {
    public void print() {
        System.out.print("false ");
    }
}

//unaire
abstract class Uop extends Expr {
    protected Expr e1;
    public abstract void print();
}
class UMinus extends Uop {
    public UMinus(Expr e1) {
        this.e1 = e1;
    }
    public void print() {
        System.out.print(" -");
        e1.print();
    }
}
class UNot extends Uop {
    public UNot(Expr e1) {
        this.e1 = e1;
    }
    public void print() {
        System.out.print(" not ");
        e1.print();
    }
}

//binaire
abstract class Bop extends Expr {
    protected Expr e1;
    protected Expr e2;
}

class BAdd extends Bop {
    public BAdd(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" + ");
        e2.print();
    }
}
class BSub extends Bop {
    public BSub(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" - ");
        e2.print();
    }
}
class BMul extends Bop {
    public BMul(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        System.out.print(" * ");
    }
}
class BDiv extends Bop {
    public BDiv(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        System.out.print(" / ");
    }
}
class BAnd extends Bop {
    public BAnd(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" and ");
        e2.print();
    }
}
class BOr extends Bop {
    public BOr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" or ");
        e2.print();
    }
}
class BInf extends Bop {
    public BInf(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" < ");
        e2.print();
    }
}
class BInfEg extends Bop {
    public BInfEg(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" <= ");
        e2.print();
    }
}
class BEg extends Bop {
    public BEg(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" = ");
        e2.print();
    }
}
class BNoEg extends Bop {
    public BNoEg(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" != ");
        e2.print();
    }
}
class BSupEg extends Bop {
    public BSupEg(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" >= ");
        e2.print();
    }
}
class BSup extends Bop {
    public BSup(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public void print() {
        e1.print();
        System.out.print(" > ");
        e2.print();
    }
}

class AppelFonction extends Expr {
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

class ArrayGet extends Expr {
    protected Expr array;
    protected Expr i;
    public ArrayGet(Expr array, Expr i) {
        this.array = array;
        this.i = i;
    }
    public void print() {
        array.print();
        System.out.print("[");
        i.print();
        System.out.print("]");
    }
}

class NewArray extends Expr {
    protected Type t;
    protected Expr s;
    public NewArray(Type t, Expr s) {
        this.t = t;
        this.s = s;
    }
    public void print() {
        System.out.print("New array of ");
        t.print();
        System.out.print("[");
        s.print();
        System.out.print("]");
    }
}

abstract class App {
    abstract public void print();
}
class Read extends App {
    public void print() {
        System.out.print("Read");
    }
}
class Write extends App {
    public void print() {
        System.out.print("Write");
    }
}
class Fun extends App {
    public String n;
    
    public Fun(String n) {
        this.n = n;
    }
    
    public void print() {
        System.out.print(this.n);
    }
}

public class langageAST3 {
    public static void main(String[] argv) {
	ANTLRInputStream stream = new ANTLRInputStream(argv[0]);
	langageAST3Lexer lexer = new langageAST3Lexer(stream);
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	langageAST3Parser parser = new langageAST3Parser(tokens);
	/*ArrayList<Expr> l = parser.listExpr().print;
	for (Expr e : l)
	    e.print();
    }*/
    Prog e = parser.prog().pr;
    e.print();
} 
    
}

