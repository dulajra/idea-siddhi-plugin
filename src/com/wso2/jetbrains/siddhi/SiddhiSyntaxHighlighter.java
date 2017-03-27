package com.wso2.jetbrains.siddhi;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.wso2.jetbrains.siddhi.adaptor.lexer.ANTLRLexerAdaptor;
import com.wso2.jetbrains.siddhi.adaptor.lexer.PSIElementTypeFactory;
import com.wso2.jetbrains.siddhi.adaptor.lexer.TokenIElementType;
import com.wso2.jetbrains.siddhi.parser.SiddhiQLLexer;
import com.wso2.jetbrains.siddhi.parser.SiddhiQLParser;
import org.jetbrains.annotations.NotNull;

/**
 * A highlighter is really just a mapping from token type to
 * some text attributes using {@link #getTokenHighlights(IElementType)}.
 * The reason that it returns an array, TextAttributesKey[], is
 * that you might want to mix the attributes of a few known highlighters.
 * A {@link TextAttributesKey} is just a name for that a theme
 * or IDE skin can set. For example, {@link com.intellij.openapi.editor.DefaultLanguageHighlighterColors#KEYWORD}
 * is the key that maps to what identifiers look like in the editor.
 * To change it, see dialog: Editor > Colors & Fonts > Language Defaults.
 * <p>
 * From <a href="http://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/syntax_highlighting_and_error_highlighting.html">doc</a>:
 * "The mapping of the TextAttributesKey to specific attributes used
 * in an editor is defined by the EditorColorsScheme class, and can
 * be configured by the user if the plugin provides an appropriate
 * configuration interface.
 * ...
 * The syntax highlighter returns the {@link TextAttributesKey}
 * instances for each token type which needs special highlighting.
 * For highlighting lexer errors, the standard TextAttributesKey
 * for bad characters HighlighterColors.BAD_CHARACTER can be used."
 */
public class SiddhiSyntaxHighlighter extends SyntaxHighlighterBase {

    static {
        PSIElementTypeFactory.defineLanguageIElementTypes(SiddhiLanguage.INSTANCE,
                SiddhiQLParser.tokenNames,
                SiddhiQLParser.ruleNames);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        SiddhiQLLexer lexer = new SiddhiQLLexer(null);
        return new ANTLRLexerAdaptor(SiddhiLanguage.INSTANCE, lexer);
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (!(tokenType instanceof TokenIElementType)) return SiddhiTokenTypes.EMPTY_KEYS;
        TokenIElementType myType = (TokenIElementType) tokenType;
        int ttype = myType.getANTLRTokenType();
        TextAttributesKey attrKey;
        switch (ttype) {
            case SiddhiQLLexer.ID:
                attrKey = SiddhiTokenTypes.ID;
                break;
            case SiddhiQLLexer.STREAM:
            case SiddhiQLLexer.DEFINE:
            case SiddhiQLLexer.TABLE:
            case SiddhiQLLexer.FROM:
            case SiddhiQLLexer.PARTITION:
            case SiddhiQLLexer.WINDOW:
            case SiddhiQLLexer.SELECT:
            case SiddhiQLLexer.GROUP:
            case SiddhiQLLexer.BY:
            case SiddhiQLLexer.HAVING:
            case SiddhiQLLexer.INSERT:
            case SiddhiQLLexer.DELETE:
            case SiddhiQLLexer.UPDATE:
            case SiddhiQLLexer.RETURN:
            case SiddhiQLLexer.EVENTS:
            case SiddhiQLLexer.INTO:
            case SiddhiQLLexer.OUTPUT:
            case SiddhiQLLexer.EXPIRED:
            case SiddhiQLLexer.CURRENT:
            case SiddhiQLLexer.SNAPSHOT:
            case SiddhiQLLexer.FOR:
            case SiddhiQLLexer.RAW:
            case SiddhiQLLexer.OF:
            case SiddhiQLLexer.AS:
            case SiddhiQLLexer.OR:
            case SiddhiQLLexer.AND:
            case SiddhiQLLexer.ON:
            case SiddhiQLLexer.IS:
            case SiddhiQLLexer.NOT:
            case SiddhiQLLexer.WITHIN:
            case SiddhiQLLexer.WITH:
            case SiddhiQLLexer.BEGIN:
            case SiddhiQLLexer.END:
            case SiddhiQLLexer.NULL:
            case SiddhiQLLexer.EVERY:
            case SiddhiQLLexer.LAST:
            case SiddhiQLLexer.ALL:
            case SiddhiQLLexer.FIRST:
            case SiddhiQLLexer.JOIN:
            case SiddhiQLLexer.INNER:
            case SiddhiQLLexer.OUTER:
            case SiddhiQLLexer.RIGHT:
            case SiddhiQLLexer.LEFT:
            case SiddhiQLLexer.FULL:
            case SiddhiQLLexer.UNIDIRECTIONAL:
            case SiddhiQLLexer.YEARS:
            case SiddhiQLLexer.MONTHS:
            case SiddhiQLLexer.WEEKS:
            case SiddhiQLLexer.DAYS:
            case SiddhiQLLexer.HOURS:
            case SiddhiQLLexer.MINUTES:
            case SiddhiQLLexer.SECONDS:
            case SiddhiQLLexer.MILLISECONDS:
            case SiddhiQLLexer.FALSE:
            case SiddhiQLLexer.TRUE:
            case SiddhiQLLexer.STRING:
            case SiddhiQLLexer.INT:
            case SiddhiQLLexer.LONG:
            case SiddhiQLLexer.FLOAT:
            case SiddhiQLLexer.DOUBLE:
            case SiddhiQLLexer.BOOL:
            case SiddhiQLLexer.OBJECT:
                attrKey = SiddhiTokenTypes.KEYWORD;
                break;
            case SiddhiQLLexer.STRING_LITERAL:
                attrKey = SiddhiTokenTypes.STRING;
                break;
            case SiddhiQLLexer.INT_LITERAL:
            case SiddhiQLLexer.LONG_LITERAL:
            case SiddhiQLLexer.FLOAT_LITERAL:
            case SiddhiQLLexer.DOUBLE_LITERAL:
                attrKey = SiddhiTokenTypes.NUMBER;
                break;
            case SiddhiQLLexer.ASSIGN:
            case SiddhiQLLexer.STAR:
            case SiddhiQLLexer.PLUS:
            case SiddhiQLLexer.MINUS:
            case SiddhiQLLexer.DIV:
            case SiddhiQLLexer.LT:
            case SiddhiQLLexer.LT_EQ:
            case SiddhiQLLexer.GT:
            case SiddhiQLLexer.GT_EQ:
            case SiddhiQLLexer.EQ:
            case SiddhiQLLexer.NOT_EQ:
            case SiddhiQLLexer.MOD:
                attrKey = SiddhiTokenTypes.OPERATION_SIGN;
                break;
            case SiddhiQLLexer.MULTILINE_COMMENT:
                attrKey = SiddhiTokenTypes.LINE_COMMENT;
                break;
            case SiddhiQLLexer.SINGLE_LINE_COMMENT:
                attrKey = SiddhiTokenTypes.BLOCK_COMMENT;
                break;
            case SiddhiQLLexer.SCOL:
            case SiddhiQLLexer.COL:
                attrKey = SiddhiTokenTypes.SEMICOLON;
                break;
            case SiddhiQLLexer.DOT:
                attrKey = SiddhiTokenTypes.DOT;
                break;
            case SiddhiQLLexer.COMMA:
                attrKey = SiddhiTokenTypes.COMMA;
                break;
            case SiddhiQLLexer.OPEN_PAR:
            case SiddhiQLLexer.CLOSE_PAR:
                attrKey = SiddhiTokenTypes.PARENTHESES;
                break;
            case SiddhiQLLexer.OPEN_SQARE_BRACKETS:
            case SiddhiQLLexer.CLOASE_SQARE_BRACKETS:
                attrKey = SiddhiTokenTypes.BRACKETS;
                break;
            default:
                return SiddhiTokenTypes.EMPTY_KEYS;
        }
        return new TextAttributesKey[]{attrKey};
    }
}
