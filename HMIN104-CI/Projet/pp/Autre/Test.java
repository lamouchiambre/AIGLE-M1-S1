package pp;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ANTLRInputStream stream = new ANTLRInputStream(argv [0]);
		Lexer lexer = new astListLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		astParser parser = new astParser(tokens);
		ArrayList<Prog> p = parser.listExpr().value ;
		for (Expr e : p)
			System.out.println(e.print());
	}
}
