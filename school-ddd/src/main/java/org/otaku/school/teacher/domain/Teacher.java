package org.otaku.school.teacher.domain;

import org.otaku.school.common.domain.AggregateRoot;
import org.otaku.school.common.domain.Entity;

public class Teacher implements Entity, AggregateRoot {
    private Long id;
    private String name;
    private String graduateSchool;
    private Education education;
}
