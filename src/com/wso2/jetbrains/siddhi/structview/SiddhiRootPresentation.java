package com.wso2.jetbrains.siddhi.structview;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiFile;
import com.wso2.jetbrains.siddhi.Icons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SiddhiRootPresentation implements ItemPresentation {
	protected final PsiFile element;

	protected SiddhiRootPresentation(PsiFile element) {
		this.element = element;
	}

	@Nullable
	@Override
	public Icon getIcon(boolean unused) {
		return Icons.SIDDHI_ICON;
	}

	@Nullable
	@Override
	public String getPresentableText() {
		return element.getVirtualFile().getNameWithoutExtension();
	}

	@Nullable
	@Override
	public String getLocationString() {
		return null;
	}
}
