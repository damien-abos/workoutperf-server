// Generated from D:/projets/workoutperf/workoutperf-server/yaon\YaonText.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link YaonTextParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface YaonTextVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#top_level}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTop_level(YaonTextParser.Top_levelContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#top_level_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTop_level_value(YaonTextParser.Top_level_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(YaonTextParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity(YaonTextParser.EntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#delimiting_entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelimiting_entity(YaonTextParser.Delimiting_entityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#keyword_delimiting_entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_delimiting_entity(YaonTextParser.Keyword_delimiting_entityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#keyword_entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_entity(YaonTextParser.Keyword_entityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#numeric_entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_entity(YaonTextParser.Numeric_entityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(YaonTextParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#quoted_annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuoted_annotation(YaonTextParser.Quoted_annotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(YaonTextParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSexp(YaonTextParser.SexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#sexp_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSexp_value(YaonTextParser.Sexp_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#sexp_delimiting_entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSexp_delimiting_entity(YaonTextParser.Sexp_delimiting_entityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#sexp_keyword_delimiting_entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSexp_keyword_delimiting_entity(YaonTextParser.Sexp_keyword_delimiting_entityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#sexp_null_delimiting_entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSexp_null_delimiting_entity(YaonTextParser.Sexp_null_delimiting_entityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#sexp_keyword_entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSexp_keyword_entity(YaonTextParser.Sexp_keyword_entityContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(YaonTextParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct(YaonTextParser.StructContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(YaonTextParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#any_null}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny_null(YaonTextParser.Any_nullContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#typed_null}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyped_null(YaonTextParser.Typed_nullContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#field_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_name(YaonTextParser.Field_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#quoted_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuoted_text(YaonTextParser.Quoted_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#symbol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbol(YaonTextParser.SymbolContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaonTextParser#ws}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWs(YaonTextParser.WsContext ctx);
}