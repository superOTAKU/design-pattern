package org.otaku.school.course.application.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.otaku.school.common.domain.cqrs.Result;

@Data
@AllArgsConstructor
public class CreateCourseResult implements Result {
    private Long id;
}
