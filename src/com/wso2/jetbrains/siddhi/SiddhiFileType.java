package com.wso2.jetbrains.siddhi;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SiddhiFileType extends LanguageFileType {
	public static final String FILE_EXTENSION = "sid";
		public static final SiddhiFileType INSTANCE = new SiddhiFileType();

	protected SiddhiFileType() {
		super(SiddhiLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public String getName() {
		return "StringTemplate v4 template group file";
	}

	@NotNull
	@Override
	public String getDescription() {
		return "StringTemplate v4 template group file";
	}

	@NotNull
	@Override
	public String getDefaultExtension() {
		return FILE_EXTENSION;
	}

	@Nullable
	@Override
	public Icon getIcon() {
		return Icons.SIDDHI_ICON;
	}
}
