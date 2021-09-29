package org.otaku.school.course.domain;

import lombok.Getter;
import lombok.Setter;
import org.otaku.school.common.domain.Entity;

import java.util.Objects;

@Getter
@Setter
public class Student implements Entity {
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
