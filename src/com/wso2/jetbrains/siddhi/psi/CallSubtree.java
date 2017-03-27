package com.wso2.jetbrains.siddhi.psi;

import com.intellij.lang.ASTNode;
import com.wso2.jetbrains.siddhi.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

public class CallSubtree extends ANTLRPsiNode {
	public CallSubtree(@NotNull ASTNode node) {
		super(node);
	}
}
