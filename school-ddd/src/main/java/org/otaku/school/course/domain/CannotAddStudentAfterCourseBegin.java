package org.otaku.school.course.domain;

import lombok.AllArgsConstructor;
import org.otaku.school.common.domain.Specification;
import org.otaku.school.common.domain.SpecificationUnSatisfiedException;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CannotAddStudentAfterCourseBegin implements Specification {
    private final LocalDateTime courseStartTime;

    @Override
    public void check() {
        if (LocalDateTime.now().compareTo(courseStartTime) > 0) {
            throw new SpecificationUnSatisfiedException("course already start!");
        }
    }

}
