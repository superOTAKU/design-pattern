package org.otaku.school.course.domain;

import lombok.Getter;
import lombok.Setter;
import org.otaku.school.common.domain.Entity;

import java.util.Objects;

@Getter
@Setter
public class Teacher implements Entity {
    private Long id;
    private String name;

    private Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Teacher create(Long id) {
        return new Teacher(id, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
