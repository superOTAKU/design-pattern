package org.otaku.school.common.domain.event;

/**
 * 事件总线，可以有不同的实现，最简单是用Spring的ApplicationEvent
 */
public interface EventBus {

    /**
     * 发布领域事件到事件总线，对事件感兴趣的模块负责监听事件并进行处理（怎么监听这里不管，只管发布事件）
     * @param event 事件
     */
    void dispatchEvent(DomainEvent event);
}
