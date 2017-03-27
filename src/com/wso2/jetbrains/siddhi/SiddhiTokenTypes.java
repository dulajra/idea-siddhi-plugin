package com.wso2.jetbrains.siddhi;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * @author Dulaj Atapattu
 */
public interface SiddhiTokenTypes {

    TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    TextAttributesKey ID =
            createTextAttributesKey("SAMPLE_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
    TextAttributesKey KEYWORD =
            createTextAttributesKey("SAMPLE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    TextAttributesKey STRING =
            createTextAttributesKey("SAMPLE_STRING", DefaultLanguageHighlighterColors.STRING);
    TextAttributesKey NUMBER =
            createTextAttributesKey("SAMPLE_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    TextAttributesKey OPERATION_SIGN =
            createTextAttributesKey("SAMPLE_OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("SAMPLE_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("SAMPLE_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    TextAttributesKey SEMICOLON =
            createTextAttributesKey("SAMPLE_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    TextAttributesKey DOT =
            createTextAttributesKey("SAMPLE_SEMICOLON", DefaultLanguageHighlighterColors.DOT);
    TextAttributesKey PARENTHESES =
            createTextAttributesKey("SAMPLE_SEMICOLON", DefaultLanguageHighlighterColors.PARENTHESES);
    TextAttributesKey BRACKETS =
            createTextAttributesKey("SAMPLE_SEMICOLON", DefaultLanguageHighlighterColors.BRACKETS);
    TextAttributesKey COMMA =
            createTextAttributesKey("SAMPLE_SEMICOLON", DefaultLanguageHighlighterColors.COMMA);

}
