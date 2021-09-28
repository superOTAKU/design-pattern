package org.otaku.school.common.domain.event;

import java.util.ArrayList;
import java.util.List;

/**
 * 持有领域事件的对象，大部分人实现在AggregateRoot的基类中，我不想放在Aggregate中，所以单独实现一个基类
 */
public class EventHolder {
    protected List<DomainEvent> events = new ArrayList<>();

    public void addEvent(DomainEvent event) {
        events.add(event);
    }

    /**
     * 拉取所有发生的事件，以便后续处理，如分发到EventBus
     */
    public List<DomainEvent> pollEvents() {
        List<DomainEvent> events = this.events;
        this.events = new ArrayList<>();
        return events;
    }
}
