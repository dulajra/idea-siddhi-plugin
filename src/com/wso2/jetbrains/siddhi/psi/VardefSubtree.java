package com.wso2.jetbrains.siddhi.psi;

import com.intellij.lang.ASTNode;
import com.wso2.jetbrains.siddhi.adaptor.psi.IdentifierDefSubtree;
import org.jetbrains.annotations.NotNull;

public class VardefSubtree extends IdentifierDefSubtree {
	public VardefSubtree(@NotNull ASTNode node) {
		// TODO: 3/25/17 null
		super(node, null);
	}
}
