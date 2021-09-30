package org.otaku.calculator.domain;

/**
 * 表达式校验器，负责构建合法的表达式
 */
public interface ExpressionVerifier {
    boolean canAdd(String expression, Character newChar);
}
