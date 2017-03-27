package com.wso2.jetbrains.siddhi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.wso2.jetbrains.siddhi.adaptor.lexer.ANTLRLexerAdaptor;
import com.wso2.jetbrains.siddhi.adaptor.lexer.PSIElementTypeFactory;
import com.wso2.jetbrains.siddhi.adaptor.lexer.RuleIElementType;
import com.wso2.jetbrains.siddhi.adaptor.lexer.TokenIElementType;
import com.wso2.jetbrains.siddhi.adaptor.parser.ANTLRParserAdaptor;
import com.wso2.jetbrains.siddhi.adaptor.psi.ANTLRPsiNode;
import com.wso2.jetbrains.siddhi.parser.SiddhiQLLexer;
import com.wso2.jetbrains.siddhi.parser.SiddhiQLParser;
import com.wso2.jetbrains.siddhi.psi.SiddhiPSIFileRoot;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SiddhiParserDefinition implements ParserDefinition {
	public static final IFileElementType FILE =
		new IFileElementType(SiddhiLanguage.INSTANCE);

	public static TokenIElementType ID;

	static {
		PSIElementTypeFactory.defineLanguageIElementTypes(SiddhiLanguage.INSTANCE,
		                                                  SiddhiQLParser.tokenNames,
		                                                  SiddhiQLParser.ruleNames);
		List<TokenIElementType> tokenIElementTypes =
			PSIElementTypeFactory.getTokenIElementTypes(SiddhiLanguage.INSTANCE);
		ID = tokenIElementTypes.get(SiddhiQLLexer.ID);
	}

	public static final TokenSet COMMENTS =
		PSIElementTypeFactory.createTokenSet(
			SiddhiLanguage.INSTANCE,
			SiddhiQLLexer.MULTILINE_COMMENT,
			SiddhiQLLexer.SINGLE_LINE_COMMENT);

	public static final TokenSet WHITESPACE =
		PSIElementTypeFactory.createTokenSet(
			SiddhiLanguage.INSTANCE,
			SiddhiQLLexer.SPACES);

	public static final TokenSet STRING =
		PSIElementTypeFactory.createTokenSet(
			SiddhiLanguage.INSTANCE,
			SiddhiQLLexer.STRING);

	@NotNull
	@Override
	public Lexer createLexer(Project project) {
		SiddhiQLLexer lexer = new SiddhiQLLexer(null);
		return new ANTLRLexerAdaptor(SiddhiLanguage.INSTANCE, lexer);
	}

	@NotNull
	public PsiParser createParser(final Project project) {
		final SiddhiQLParser parser = new SiddhiQLParser(null);
		return new ANTLRParserAdaptor(SiddhiLanguage.INSTANCE, parser) {
			@Override
			protected ParseTree parse(Parser parser, IElementType root) {
				// start rule depends on root passed in; sometimes we want to create an ID node etc...
				if ( root instanceof IFileElementType ) {
					return null;
					// TODO: 3/25/17 commented
//					return ((SiddhiQLParser) parser).script();
				}
				// let's hope it's an ID as needed by "rename function"
				return null;
				// TODO: 3/25/17 commented
//				return ((SiddhiQLParser) parser).primary();
			}
		};
	}

	/** "Tokens of those types are automatically skipped by PsiBuilder." */
	@NotNull
	public TokenSet getWhitespaceTokens() {
		return WHITESPACE;
	}

	@NotNull
	public TokenSet getCommentTokens() {
		return COMMENTS;
	}

	@NotNull
	public TokenSet getStringLiteralElements() {
		return STRING;
	}

	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
		return SpaceRequirements.MAY;
	}

	/** What is the IFileElementType of the root parse tree node? It
	 *  is called from {@link #createFile(FileViewProvider)} at least.
	 */
	@Override
	public IFileElementType getFileNodeType() {
		return FILE;
	}

	/** Create the root of your PSI tree (a PsiFile).
	 *
	 *  From IntelliJ IDEA Architectural Overview:
	 *  "A PSI (Program Structure Interface) file is the root of a structure
	 *  representing the contents of a file as a hierarchy of elements
	 *  in a particular programming language."
	 *
	 *  PsiFile is to be distinguished from a FileASTNode, which is a parse
	 *  tree node that eventually becomes a PsiFile. From PsiFile, we can get
	 *  it back via: {@link PsiFile#getNode}.
	 */
	@Override
	public PsiFile createFile(FileViewProvider viewProvider) {
		return new SiddhiPSIFileRoot(viewProvider);
	}

	/** Convert from *NON-LEAF* parse node (AST they call it)
	 *  to PSI node. Leaves are created in the AST factory.
	 *  Rename re-factoring can cause this to be
	 *  called on a TokenIElementType since we want to rename ID nodes.
	 *  In that case, this method is called to create the root node
	 *  but with ID type. Kind of strange, but we can simply create a
	 *  ASTWrapperPsiElement to make everything work correctly.
	 *
	 *  RuleIElementType.  Ah! It's that ID is the root
	 *  IElementType requested to parse, which means that the root
	 *  node returned from parsetree->PSI conversion.  But, it
	 *  must be a CompositeElement! The adaptor calls
	 *  rootMarker.done(root) to finish off the PSI conversion.
	 *  See {@link ANTLRParserAdaptor#parse(IElementType root,
	 *  PsiBuilder)}
	 *
	 *  If you don't care to distinguish PSI nodes by type, it is
	 *  sufficient to create a {@link ANTLRPsiNode} around
	 *  the parse tree node
	 */
	@NotNull
	public PsiElement createElement(ASTNode node) {
		IElementType elType = node.getElementType();
		if ( elType instanceof TokenIElementType ) {
			return new ANTLRPsiNode(node);
		}
		if ( !(elType instanceof RuleIElementType) ) {
			return new ANTLRPsiNode(node);
		}
		RuleIElementType ruleElType = (RuleIElementType) elType;
		switch ( ruleElType.getRuleIndex() ) {
			// TODO: 3/25/17 commented
			/*case SiddhiQLParser.RULE_function :
				return new FunctionSubtree(node);
			case SiddhiQLParser.RULE_vardef :
				return new VardefSubtree(node);
			case SiddhiQLParser.RULE_formal_arg :
				return new ArgdefSubtree(node);
			case SiddhiQLParser.RULE_block :
				return new BlockSubtree(node);
			case SiddhiQLParser.RULE_call_expr :
				return new CallSubtree(node);*/
			default :
				return new ANTLRPsiNode(node);
		}
	}
}
