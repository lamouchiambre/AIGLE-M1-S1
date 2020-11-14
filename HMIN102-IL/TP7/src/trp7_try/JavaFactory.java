package trp7_try;

import tp7.ScanedText;

public class JavaFactory extends CompilerFactory {

	public JavaFactory() {
		supportedLanguage = "Java";
	}
	
	public Lexer createLexer() {
		return new JavaLexer();
	}
	
	class JavaLexer extends Lexer {
		public ScanedText scan(ProgramText t) {
			System.out.println("I am scanning a Java program text");
			return new ScanedText();
		}
	}
}
