package org.otaku.school.common.infrastructure.event.spring;

import org.otaku.school.common.domain.event.DomainEvent;
import org.otaku.school.common.domain.event.EventBus;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collection;

/**
 * 使用Spring提供的Event发布监听机制，不用@Service注解，想要更灵活一些
 */
public class SpringEventBus implements EventBus {
    private final ApplicationEventPublisher publisher;

    public SpringEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void dispatchEvent(DomainEvent event) {
        publisher.publishEvent(event);
    }

    @Override
    public void dispatchEvents(Collection<? extends DomainEvent> events) {
        events.forEach(this::dispatchEvent);
    }
}
