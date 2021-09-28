package org.otaku.school.common.domain.cqrs;

/**
 * query总线，根据Query对象匹配QueryHandler
 */
public interface QueryBus {

    <Q extends Query, R extends Result> QueryHandler<Q, R> getQueryHandler(Q query);

}
