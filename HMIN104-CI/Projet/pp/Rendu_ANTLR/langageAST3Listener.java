// Generated from langageAST3.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link langageAST3Parser}.
 */
public interface langageAST3Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#listexpr}.
	 * @param ctx the parse tree
	 */
	void enterListexpr(langageAST3Parser.ListexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#listexpr}.
	 * @param ctx the parse tree
	 */
	void exitListexpr(langageAST3Parser.ListexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#listDec}.
	 * @param ctx the parse tree
	 */
	void enterListDec(langageAST3Parser.ListDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#listDec}.
	 * @param ctx the parse tree
	 */
	void exitListDec(langageAST3Parser.ListDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(langageAST3Parser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(langageAST3Parser.DecContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#listeDecVar}.
	 * @param ctx the parse tree
	 */
	void enterListeDecVar(langageAST3Parser.ListeDecVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#listeDecVar}.
	 * @param ctx the parse tree
	 */
	void exitListeDecVar(langageAST3Parser.ListeDecVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#listeDecFonc}.
	 * @param ctx the parse tree
	 */
	void enterListeDecFonc(langageAST3Parser.ListeDecFoncContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#listeDecFonc}.
	 * @param ctx the parse tree
	 */
	void exitListeDecFonc(langageAST3Parser.ListeDecFoncContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#decVar}.
	 * @param ctx the parse tree
	 */
	void enterDecVar(langageAST3Parser.DecVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#decVar}.
	 * @param ctx the parse tree
	 */
	void exitDecVar(langageAST3Parser.DecVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#decPar}.
	 * @param ctx the parse tree
	 */
	void enterDecPar(langageAST3Parser.DecParContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#decPar}.
	 * @param ctx the parse tree
	 */
	void exitDecPar(langageAST3Parser.DecParContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(langageAST3Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(langageAST3Parser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#decFonc}.
	 * @param ctx the parse tree
	 */
	void enterDecFonc(langageAST3Parser.DecFoncContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#decFonc}.
	 * @param ctx the parse tree
	 */
	void exitDecFonc(langageAST3Parser.DecFoncContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#listDecPar}.
	 * @param ctx the parse tree
	 */
	void enterListDecPar(langageAST3Parser.ListDecParContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#listDecPar}.
	 * @param ctx the parse tree
	 */
	void exitListDecPar(langageAST3Parser.ListDecParContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#instr}.
	 * @param ctx the parse tree
	 */
	void enterInstr(langageAST3Parser.InstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#instr}.
	 * @param ctx the parse tree
	 */
	void exitInstr(langageAST3Parser.InstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(langageAST3Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(langageAST3Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(langageAST3Parser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(langageAST3Parser.FunContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#app}.
	 * @param ctx the parse tree
	 */
	void enterApp(langageAST3Parser.AppContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#app}.
	 * @param ctx the parse tree
	 */
	void exitApp(langageAST3Parser.AppContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST3Parser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(langageAST3Parser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST3Parser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(langageAST3Parser.TypeContext ctx);
}