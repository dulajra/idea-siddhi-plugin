package com.wso2.jetbrains.siddhi.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.wso2.jetbrains.siddhi.SiddhiLanguage;
import com.wso2.jetbrains.siddhi.adaptor.SymtabUtils;
import com.wso2.jetbrains.siddhi.adaptor.psi.ANTLRPsiNode;
import com.wso2.jetbrains.siddhi.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockSubtree extends ANTLRPsiNode implements ScopeNode {
	public BlockSubtree(@NotNull ASTNode node) {
		super(node);
	}

	@Nullable
	@Override
	public PsiElement resolve(PsiNamedElement element) {
//		System.out.println(getClass().getSimpleName()+
//		                   ".resolve("+element.getName()+
//		                   " at "+Integer.toHexString(element.hashCode())+")");

		return SymtabUtils.resolve(this, SiddhiLanguage.INSTANCE,
		                           element, "/block/vardef/ID");
	}
}
