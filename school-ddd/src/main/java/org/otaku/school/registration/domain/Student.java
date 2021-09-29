package org.otaku.school.registration.domain;

import org.otaku.school.common.domain.AggregateRoot;
import org.otaku.school.common.domain.Entity;

public class Student implements AggregateRoot, Entity {
    private Long id;
    private String name;
    private String email;
    private Sex sex;
}
