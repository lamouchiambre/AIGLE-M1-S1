package trp7_try;

import java.io.File;

public class Compiler {
	protected CompilerFactory factory;
	protected Lexer lexer;
	protected Parser parser;
	protected PrettyPrinter prettyprinter;
	protected Generator gen;
	
	public Compiler(String language) throws Exception {
		factory = CompilerFactory.getFactory(language);
	}
	
	public void compile(ProgramText t) {
		System.out.println("Compiling a " + factory.getLanguage() + " program.");
		lexer = factory.createLexer();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("-----------------------");
			Compiler c1 = new Compiler("Java");
			c1.compile(new ProgramText("..."));
			System.out.println("-----------------------");
			Compiler c2 = new Compiler ("C++");
			c2.compile(new ProgramText("..."));
			System.out.println("-----------------------");
			Compiler c3 = new Compiler("ADA");
		} catch(Exception e) { System.out.println(e.getMessage()); }
	}
}

abstract class Lexer { public abstract ScanedText scan(ProgramText t); }
abstract class Parser { public abstract AST parse(ScanedText t); }
abstract class PrettyPrinter { public abstract ProgramText prettyPrint(AST a); }
abstract class Generator { public abstract File generate(AST a); }

class ScanedText {}

class ProgramText {
	String text;
	ProgramText(String t) {
		this.text = t;
	}
}

class AST {}