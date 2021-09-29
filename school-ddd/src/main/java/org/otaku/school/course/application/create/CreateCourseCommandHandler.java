package org.otaku.school.course.application.create;

import org.otaku.school.common.domain.cqrs.CommandHandler;
import org.otaku.school.common.domain.event.EventBus;
import org.otaku.school.common.domain.event.course.CourseCreatedEvent;
import org.otaku.school.course.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class CreateCourseCommandHandler implements CommandHandler<CreateCourseCommand, CreateCourseResult> {
    private final CourseRepository repository;
    private final EventBus eventBus;

    public CreateCourseCommandHandler(EventBus eventBus, CourseRepository repository) {
        this.eventBus = eventBus;
        this.repository = repository;
    }

    @Transactional
    @Override
    public CreateCourseResult handle(CreateCourseCommand command) {
        Course course = Course.create(null, Teacher.create(command.getTeacherId()),
                new CourseInformation(command.getName(), command.getStartTime(), command.getMinutes()),
                Collections.emptyList());
        //repository负责分配id
        repository.save(course);
        eventBus.dispatchEvent(new CourseCreatedEvent(course.getId()));
        return new CreateCourseResult(course.getId());
    }

}
