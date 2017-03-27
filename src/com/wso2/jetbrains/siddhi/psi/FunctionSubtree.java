package com.wso2.jetbrains.siddhi.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.wso2.jetbrains.siddhi.SiddhiLanguage;
import com.wso2.jetbrains.siddhi.adaptor.SymtabUtils;
import com.wso2.jetbrains.siddhi.adaptor.psi.IdentifierDefSubtree;
import com.wso2.jetbrains.siddhi.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** A subtree associated with a function definition.
 *  Its scope is the set of arguments.
 */
public class FunctionSubtree extends IdentifierDefSubtree implements ScopeNode {
	public FunctionSubtree(@NotNull ASTNode node) {
		// TODO: 3/25/17  added null
		super(node, null);
	}

	@Nullable
	@Override
	public PsiElement resolve(PsiNamedElement element) {
//		System.out.println(getClass().getSimpleName()+
//			                   ".resolve("+myElement.getName()+
//			                   " at "+Integer.toHexString(myElement.hashCode())+")");
		return SymtabUtils.resolve(this, SiddhiLanguage.INSTANCE,
		                           element, "/script/function/ID");
	}
}
