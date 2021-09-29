package org.otaku.school.course.application.getdetail;

import org.otaku.school.common.domain.cqrs.QueryHandler;
import org.otaku.school.course.domain.Course;
import org.otaku.school.course.domain.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseDetailQueryHandler implements QueryHandler<CourseDetailQuery, CourseDetailResult> {
    private final CourseRepository repository;
    public CourseDetailQueryHandler(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public CourseDetailResult handle(CourseDetailQuery query) {
        Course course = repository.getById(query.getId());
        return CourseDetailResult.create(course);
    }
}
