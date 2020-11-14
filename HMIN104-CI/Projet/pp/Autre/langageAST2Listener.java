// Generated from langageAST2.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link langageAST2Parser}.
 */
public interface langageAST2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(langageAST2Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(langageAST2Parser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#listeDecVar}.
	 * @param ctx the parse tree
	 */
	void enterListeDecVar(langageAST2Parser.ListeDecVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#listeDecVar}.
	 * @param ctx the parse tree
	 */
	void exitListeDecVar(langageAST2Parser.ListeDecVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#listeDecFonc}.
	 * @param ctx the parse tree
	 */
	void enterListeDecFonc(langageAST2Parser.ListeDecFoncContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#listeDecFonc}.
	 * @param ctx the parse tree
	 */
	void exitListeDecFonc(langageAST2Parser.ListeDecFoncContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#decVar}.
	 * @param ctx the parse tree
	 */
	void enterDecVar(langageAST2Parser.DecVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#decVar}.
	 * @param ctx the parse tree
	 */
	void exitDecVar(langageAST2Parser.DecVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#decFonc}.
	 * @param ctx the parse tree
	 */
	void enterDecFonc(langageAST2Parser.DecFoncContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#decFonc}.
	 * @param ctx the parse tree
	 */
	void exitDecFonc(langageAST2Parser.DecFoncContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#listDecPar}.
	 * @param ctx the parse tree
	 */
	void enterListDecPar(langageAST2Parser.ListDecParContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#listDecPar}.
	 * @param ctx the parse tree
	 */
	void exitListDecPar(langageAST2Parser.ListDecParContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#instr}.
	 * @param ctx the parse tree
	 */
	void enterInstr(langageAST2Parser.InstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#instr}.
	 * @param ctx the parse tree
	 */
	void exitInstr(langageAST2Parser.InstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#instraffect}.
	 * @param ctx the parse tree
	 */
	void enterInstraffect(langageAST2Parser.InstraffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#instraffect}.
	 * @param ctx the parse tree
	 */
	void exitInstraffect(langageAST2Parser.InstraffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#instrsi}.
	 * @param ctx the parse tree
	 */
	void enterInstrsi(langageAST2Parser.InstrsiContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#instrsi}.
	 * @param ctx the parse tree
	 */
	void exitInstrsi(langageAST2Parser.InstrsiContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#instrsinon}.
	 * @param ctx the parse tree
	 */
	void enterInstrsinon(langageAST2Parser.InstrsinonContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#instrsinon}.
	 * @param ctx the parse tree
	 */
	void exitInstrsinon(langageAST2Parser.InstrsinonContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#instrtantque}.
	 * @param ctx the parse tree
	 */
	void enterInstrtantque(langageAST2Parser.InstrtantqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#instrtantque}.
	 * @param ctx the parse tree
	 */
	void exitInstrtantque(langageAST2Parser.InstrtantqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#instrappel}.
	 * @param ctx the parse tree
	 */
	void enterInstrappel(langageAST2Parser.InstrappelContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#instrappel}.
	 * @param ctx the parse tree
	 */
	void exitInstrappel(langageAST2Parser.InstrappelContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#instrecriture}.
	 * @param ctx the parse tree
	 */
	void enterInstrecriture(langageAST2Parser.InstrecritureContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#instrecriture}.
	 * @param ctx the parse tree
	 */
	void exitInstrecriture(langageAST2Parser.InstrecritureContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#appelfct}.
	 * @param ctx the parse tree
	 */
	void enterAppelfct(langageAST2Parser.AppelfctContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#appelfct}.
	 * @param ctx the parse tree
	 */
	void exitAppelfct(langageAST2Parser.AppelfctContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(langageAST2Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(langageAST2Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#exprBool}.
	 * @param ctx the parse tree
	 */
	void enterExprBool(langageAST2Parser.ExprBoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#exprBool}.
	 * @param ctx the parse tree
	 */
	void exitExprBool(langageAST2Parser.ExprBoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#exprInt}.
	 * @param ctx the parse tree
	 */
	void enterExprInt(langageAST2Parser.ExprIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#exprInt}.
	 * @param ctx the parse tree
	 */
	void exitExprInt(langageAST2Parser.ExprIntContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopInt}.
	 * @param ctx the parse tree
	 */
	void enterBopInt(langageAST2Parser.BopIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopInt}.
	 * @param ctx the parse tree
	 */
	void exitBopInt(langageAST2Parser.BopIntContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopInt2}.
	 * @param ctx the parse tree
	 */
	void enterBopInt2(langageAST2Parser.BopInt2Context ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopInt2}.
	 * @param ctx the parse tree
	 */
	void exitBopInt2(langageAST2Parser.BopInt2Context ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopInt3}.
	 * @param ctx the parse tree
	 */
	void enterBopInt3(langageAST2Parser.BopInt3Context ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopInt3}.
	 * @param ctx the parse tree
	 */
	void exitBopInt3(langageAST2Parser.BopInt3Context ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopInt4}.
	 * @param ctx the parse tree
	 */
	void enterBopInt4(langageAST2Parser.BopInt4Context ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopInt4}.
	 * @param ctx the parse tree
	 */
	void exitBopInt4(langageAST2Parser.BopInt4Context ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopInt5}.
	 * @param ctx the parse tree
	 */
	void enterBopInt5(langageAST2Parser.BopInt5Context ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopInt5}.
	 * @param ctx the parse tree
	 */
	void exitBopInt5(langageAST2Parser.BopInt5Context ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopInt6}.
	 * @param ctx the parse tree
	 */
	void enterBopInt6(langageAST2Parser.BopInt6Context ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopInt6}.
	 * @param ctx the parse tree
	 */
	void exitBopInt6(langageAST2Parser.BopInt6Context ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopBool}.
	 * @param ctx the parse tree
	 */
	void enterBopBool(langageAST2Parser.BopBoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopBool}.
	 * @param ctx the parse tree
	 */
	void exitBopBool(langageAST2Parser.BopBoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopBool1}.
	 * @param ctx the parse tree
	 */
	void enterBopBool1(langageAST2Parser.BopBool1Context ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopBool1}.
	 * @param ctx the parse tree
	 */
	void exitBopBool1(langageAST2Parser.BopBool1Context ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bopBool2}.
	 * @param ctx the parse tree
	 */
	void enterBopBool2(langageAST2Parser.BopBool2Context ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bopBool2}.
	 * @param ctx the parse tree
	 */
	void exitBopBool2(langageAST2Parser.BopBool2Context ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(langageAST2Parser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(langageAST2Parser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(langageAST2Parser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(langageAST2Parser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link langageAST2Parser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(langageAST2Parser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link langageAST2Parser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(langageAST2Parser.BoolContext ctx);
}