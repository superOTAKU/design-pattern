package org.otaku.school.registration.domain;

import org.otaku.school.common.domain.ValueObject;

public enum Sex implements ValueObject {
    MALE(1), FEMALE(2)
    ;
    private int sex;

    Sex(int sex) {
        this.sex = sex;
    }
}
