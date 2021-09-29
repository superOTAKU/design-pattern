package org.otaku.school.teacher.domain;

/**
 * 学历
 */
public enum Education {
    BACHELOR(1),
    MASTER(2),
    DOCTOR(3)
    ;
    private int type;

    private Education(int type) {
        this.type = type;
    }
}
