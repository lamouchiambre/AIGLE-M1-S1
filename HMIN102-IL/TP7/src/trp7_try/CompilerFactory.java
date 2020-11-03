package trp7_try;

public abstract class CompilerFactory {
	protected String supportedLanguage;
	
	public String getLanguage() { return supportedLanguage; }
	
	public static CompilerFactory getFactory(String language) throws Exception {
		if (language.equals("Java")) {
			return (new JavaFactory());
		} else if (language.equals("C++")) {
			return (new CPPFactory());
		} else throw new Exception("Non supported Language : " + language + ", Extend the framework to support it");
	}

	public abstract Lexer createLexer();
		
}
