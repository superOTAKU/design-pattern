package org.otaku.school.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.otaku.school.common.domain.ValueObject;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseInformation implements ValueObject {
    //课程名称
    private String name;
    //课程时间安排，每周几天，几点开始，几点结束
    private LocalDateTime startTime;
    //课程持续时间
    private Long minutes;
}
