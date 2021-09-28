package org.otaku.school.common.domain.cqrs;

/**
 * 根据Command类型匹配CommandHandler
 */
public interface CommandBus {

    <C extends Command, R extends Result> CommandHandler<C, R> getCommandHandler(C command);

}
