package com.wso2.jetbrains.siddhi.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.wso2.jetbrains.siddhi.Icons;
import com.wso2.jetbrains.siddhi.SiddhiLanguage;
import com.wso2.jetbrains.siddhi.SiddhiFileType;
import com.wso2.jetbrains.siddhi.adaptor.SymtabUtils;
import com.wso2.jetbrains.siddhi.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SiddhiPSIFileRoot extends PsiFileBase implements ScopeNode {
    public SiddhiPSIFileRoot(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SiddhiLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SiddhiFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Sample Language file";
    }

    @Override
    public Icon getIcon(int flags) {
        return Icons.SIDDHI_ICON;
    }

	/** Return null since a file scope has no enclosing scope. It is
	 *  not itself in a scope.
	 */
	@Override
	public ScopeNode getContext() {
		return null;
	}

	@Nullable
	@Override
	public PsiElement resolve(PsiNamedElement element) {
//		System.out.println(getClass().getSimpleName()+
//		                   ".resolve("+element.getName()+
//		                   " at "+Integer.toHexString(element.hashCode())+")");
		if ( element.getParent() instanceof CallSubtree ) {
			return SymtabUtils.resolve(this, SiddhiLanguage.INSTANCE,
			                           element, "/script/function/ID");
		}
		return SymtabUtils.resolve(this, SiddhiLanguage.INSTANCE,
		                           element, "/script/vardef/ID");
	}
}
