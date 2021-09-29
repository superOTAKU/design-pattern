package org.otaku.school.course.application.create;

import lombok.Data;
import org.otaku.school.common.domain.cqrs.Command;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateCourseCommand implements Command {
    @NotBlank
    private String name;
    @NotNull
    private Long teacherId;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    @Min(0)
    @Max(120)
    private Long minutes;
}
