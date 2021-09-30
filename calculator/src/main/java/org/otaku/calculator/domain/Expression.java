package org.otaku.calculator.domain;

import java.math.BigDecimal;

/**
 * 表达式是一个能返回计算结果的对象
 */
public interface Expression {

    BigDecimal execute();

}
