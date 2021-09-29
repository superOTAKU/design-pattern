package org.otaku.school.common.domain;

import java.util.Objects;

public class LongValueObject implements ValueObject {
    private final Long value;

    public LongValueObject(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //允许子类复用equals
        if (!(o instanceof LongValueObject)) return false;
        LongValueObject that = (LongValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Long getValue() {
        return value;
    }
}
