package org.otaku.school.common.domain.cqrs;

/**
 * 查询处理器
 * @param <Q> 查询对象
 * @param <R> 返回结果
 */
public interface QueryHandler<Q extends Query, R extends Result> {
    R handle(Q query);
}
