package org.otaku.school.common.infrastructure.cqrs;

import lombok.extern.slf4j.Slf4j;
import org.otaku.school.common.domain.cqrs.*;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service
@Slf4j
public class InMemoryQueryCommandBus implements QueryBus, CommandBus {
    private final Map<Class<? extends Query>, Class<? extends QueryHandler>> queryMapping = new HashMap<>();
    private final Map<Class<? extends Command>, Class<? extends CommandHandler>> commandMapping = new HashMap<>();
    private static final String BASE_PACKAGE = "org.otaku.school";
    private final ApplicationContext context;

    public InMemoryQueryCommandBus(ApplicationContext context) {
        this.context = context;
        initMapping();
    }

    private void initMapping() {
        doMapping(QueryHandler.class, Query.class, queryMapping);
        doMapping(CommandHandler.class, Command.class, commandMapping);
    }

    private <T> void doMapping(Class<T> baseHandlerClass, Class baseComponentClass, Map mapping) {
        try {
            Reflections reflections = new Reflections(BASE_PACKAGE);
            Set<Class<? extends T>> classes = reflections.getSubTypesOf(baseHandlerClass);
            for (var clazz : classes) {
                Type[] genericInterfaces = clazz.getGenericInterfaces();
                ParameterizedType queryHandlerInterface = (ParameterizedType) genericInterfaces[0];
                var type = queryHandlerInterface.getActualTypeArguments()[0];
                assert baseComponentClass.isAssignableFrom((Class<?>)type);
                mapping.put(type, clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <Q extends Query, R extends Result> QueryHandler<Q, R> getQueryHandler(Q query) {
        Class<? extends QueryHandler> queryHandlerType = queryMapping.get(query.getClass());
        return context.getBean(queryHandlerType);
    }

    @Override
    public <C extends Command, R extends Result> CommandHandler<C, R> getCommandHandler(C command) {
        Class<? extends CommandHandler> commandHandlerType = commandMapping.get(command.getClass());
        return context.getBean(commandHandlerType);
    }
}
