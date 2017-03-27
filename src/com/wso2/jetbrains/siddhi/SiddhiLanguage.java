package com.wso2.jetbrains.siddhi;

import com.intellij.lang.Language;

public class SiddhiLanguage extends Language {
    public static final SiddhiLanguage INSTANCE = new SiddhiLanguage();

    private SiddhiLanguage() {
        super("Siddhi");
    }
}
