package com.wso2.jetbrains.siddhi.structview;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.wso2.jetbrains.siddhi.Icons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SiddhiItemPresentation implements ItemPresentation {
	protected final PsiElement element;

	protected SiddhiItemPresentation(PsiElement element) {
		this.element = element;
	}

	@Nullable
	@Override
	public Icon getIcon(boolean unused) {
		return Icons.FUNC_ICON;
	}

	@Nullable
	@Override
	public String getPresentableText() {
		ASTNode node = element.getNode();
		return node.getText();
	}

	@Nullable
	@Override
	public String getLocationString() {
		return null;
	}
}
