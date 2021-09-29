package org.otaku.school.course.application.getdetail;

import lombok.Data;
import org.otaku.school.common.domain.cqrs.Query;

import javax.validation.constraints.NotNull;

@Data
public class CourseDetailQuery implements Query {
    @NotNull
    private Long id;
}
