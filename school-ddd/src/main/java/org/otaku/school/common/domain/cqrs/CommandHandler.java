package org.otaku.school.common.domain.cqrs;

/**
 * 命令处理器
 *
 * @param <C> 命令对象
 * @param <R> 处理结果 命令也需要结果，比如add一个student，除非外部生成uuid，否则自增id的情况下需要返回新生成student的id
 */
public interface CommandHandler<C extends Command, R extends Result> {

    R handle(C command);

}
