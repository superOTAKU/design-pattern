package org.otaku.school.common.domain.event.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.otaku.school.common.domain.event.DomainEvent;

/**
 * 课程创建事件
 *
 * 既然用到了事件，至少事件是需要被各个module共享的，否则就没有用事件的意义了(module之间间接的通信，同步或者异步)
 *
 */
@Getter
@AllArgsConstructor
public class CourseCreatedEvent implements DomainEvent {
    private final Long courseId;
}
