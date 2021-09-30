package org.otaku.calculator.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * 计算器，负责把字符串解析成为表达式
 */
public interface Calculator {

    /**
     * 计算历史
     */
    List<History> getHistories();

    /**
     * 解析表达式
     */
    BigDecimal parseExpression(String expression);

}
