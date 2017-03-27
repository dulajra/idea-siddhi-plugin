package com.wso2.jetbrains.siddhi.structview;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class SiddhiStructureViewRootElement extends SiddhiStructureViewElement {
	public SiddhiStructureViewRootElement(PsiFile element) {
		super(element);
	}

	@NotNull
	@Override
	public ItemPresentation getPresentation() {
		return new SiddhiRootPresentation((PsiFile)element);
	}
}
