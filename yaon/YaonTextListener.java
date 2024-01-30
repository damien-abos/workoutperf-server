// Generated from YaonText.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YaonTextParser}.
 */
public interface YaonTextListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#top_level}.
	 * @param ctx the parse tree
	 */
	void enterTop_level(YaonTextParser.Top_levelContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#top_level}.
	 * @param ctx the parse tree
	 */
	void exitTop_level(YaonTextParser.Top_levelContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#top_level_value}.
	 * @param ctx the parse tree
	 */
	void enterTop_level_value(YaonTextParser.Top_level_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#top_level_value}.
	 * @param ctx the parse tree
	 */
	void exitTop_level_value(YaonTextParser.Top_level_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(YaonTextParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(YaonTextParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#entity}.
	 * @param ctx the parse tree
	 */
	void enterEntity(YaonTextParser.EntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#entity}.
	 * @param ctx the parse tree
	 */
	void exitEntity(YaonTextParser.EntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void enterDelimiting_entity(YaonTextParser.Delimiting_entityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void exitDelimiting_entity(YaonTextParser.Delimiting_entityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#keyword_delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void enterKeyword_delimiting_entity(YaonTextParser.Keyword_delimiting_entityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#keyword_delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void exitKeyword_delimiting_entity(YaonTextParser.Keyword_delimiting_entityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#keyword_entity}.
	 * @param ctx the parse tree
	 */
	void enterKeyword_entity(YaonTextParser.Keyword_entityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#keyword_entity}.
	 * @param ctx the parse tree
	 */
	void exitKeyword_entity(YaonTextParser.Keyword_entityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#numeric_entity}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_entity(YaonTextParser.Numeric_entityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#numeric_entity}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_entity(YaonTextParser.Numeric_entityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(YaonTextParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(YaonTextParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#quoted_annotation}.
	 * @param ctx the parse tree
	 */
	void enterQuoted_annotation(YaonTextParser.Quoted_annotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#quoted_annotation}.
	 * @param ctx the parse tree
	 */
	void exitQuoted_annotation(YaonTextParser.Quoted_annotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(YaonTextParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(YaonTextParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#sexp}.
	 * @param ctx the parse tree
	 */
	void enterSexp(YaonTextParser.SexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#sexp}.
	 * @param ctx the parse tree
	 */
	void exitSexp(YaonTextParser.SexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#sexp_value}.
	 * @param ctx the parse tree
	 */
	void enterSexp_value(YaonTextParser.Sexp_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#sexp_value}.
	 * @param ctx the parse tree
	 */
	void exitSexp_value(YaonTextParser.Sexp_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#sexp_delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void enterSexp_delimiting_entity(YaonTextParser.Sexp_delimiting_entityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#sexp_delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void exitSexp_delimiting_entity(YaonTextParser.Sexp_delimiting_entityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#sexp_keyword_delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void enterSexp_keyword_delimiting_entity(YaonTextParser.Sexp_keyword_delimiting_entityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#sexp_keyword_delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void exitSexp_keyword_delimiting_entity(YaonTextParser.Sexp_keyword_delimiting_entityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#sexp_null_delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void enterSexp_null_delimiting_entity(YaonTextParser.Sexp_null_delimiting_entityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#sexp_null_delimiting_entity}.
	 * @param ctx the parse tree
	 */
	void exitSexp_null_delimiting_entity(YaonTextParser.Sexp_null_delimiting_entityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#sexp_keyword_entity}.
	 * @param ctx the parse tree
	 */
	void enterSexp_keyword_entity(YaonTextParser.Sexp_keyword_entityContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#sexp_keyword_entity}.
	 * @param ctx the parse tree
	 */
	void exitSexp_keyword_entity(YaonTextParser.Sexp_keyword_entityContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(YaonTextParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(YaonTextParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(YaonTextParser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(YaonTextParser.StructContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(YaonTextParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(YaonTextParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#any_null}.
	 * @param ctx the parse tree
	 */
	void enterAny_null(YaonTextParser.Any_nullContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#any_null}.
	 * @param ctx the parse tree
	 */
	void exitAny_null(YaonTextParser.Any_nullContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#typed_null}.
	 * @param ctx the parse tree
	 */
	void enterTyped_null(YaonTextParser.Typed_nullContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#typed_null}.
	 * @param ctx the parse tree
	 */
	void exitTyped_null(YaonTextParser.Typed_nullContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#field_name}.
	 * @param ctx the parse tree
	 */
	void enterField_name(YaonTextParser.Field_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#field_name}.
	 * @param ctx the parse tree
	 */
	void exitField_name(YaonTextParser.Field_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#quoted_text}.
	 * @param ctx the parse tree
	 */
	void enterQuoted_text(YaonTextParser.Quoted_textContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#quoted_text}.
	 * @param ctx the parse tree
	 */
	void exitQuoted_text(YaonTextParser.Quoted_textContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(YaonTextParser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(YaonTextParser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaonTextParser#ws}.
	 * @param ctx the parse tree
	 */
	void enterWs(YaonTextParser.WsContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaonTextParser#ws}.
	 * @param ctx the parse tree
	 */
	void exitWs(YaonTextParser.WsContext ctx);
}