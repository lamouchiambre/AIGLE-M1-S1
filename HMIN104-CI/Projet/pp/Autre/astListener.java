// Generated from ast.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link astParser}.
 */
public interface astListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link astParser#listexpr}.
	 * @param ctx the parse tree
	 */
	void enterListexpr(astParser.ListexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#listexpr}.
	 * @param ctx the parse tree
	 */
	void exitListexpr(astParser.ListexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#listaffec}.
	 * @param ctx the parse tree
	 */
	void enterListaffec(astParser.ListaffecContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#listaffec}.
	 * @param ctx the parse tree
	 */
	void exitListaffec(astParser.ListaffecContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#listaffecvar}.
	 * @param ctx the parse tree
	 */
	void enterListaffecvar(astParser.ListaffecvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#listaffecvar}.
	 * @param ctx the parse tree
	 */
	void exitListaffecvar(astParser.ListaffecvarContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#listf}.
	 * @param ctx the parse tree
	 */
	void enterListf(astParser.ListfContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#listf}.
	 * @param ctx the parse tree
	 */
	void exitListf(astParser.ListfContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(astParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(astParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#proc}.
	 * @param ctx the parse tree
	 */
	void enterProc(astParser.ProcContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#proc}.
	 * @param ctx the parse tree
	 */
	void exitProc(astParser.ProcContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#inst}.
	 * @param ctx the parse tree
	 */
	void enterInst(astParser.InstContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#inst}.
	 * @param ctx the parse tree
	 */
	void exitInst(astParser.InstContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#affec}.
	 * @param ctx the parse tree
	 */
	void enterAffec(astParser.AffecContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#affec}.
	 * @param ctx the parse tree
	 */
	void exitAffec(astParser.AffecContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#affecvar}.
	 * @param ctx the parse tree
	 */
	void enterAffecvar(astParser.AffecvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#affecvar}.
	 * @param ctx the parse tree
	 */
	void exitAffecvar(astParser.AffecvarContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(astParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(astParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(astParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(astParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#app}.
	 * @param ctx the parse tree
	 */
	void enterApp(astParser.AppContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#app}.
	 * @param ctx the parse tree
	 */
	void exitApp(astParser.AppContext ctx);
	/**
	 * Enter a parse tree produced by {@link astParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(astParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link astParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(astParser.TypeContext ctx);
}