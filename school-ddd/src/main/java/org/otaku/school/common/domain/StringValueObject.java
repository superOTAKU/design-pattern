package org.otaku.school.common.domain;

import java.util.Objects;

public abstract class StringValueObject implements ValueObject {
    private final String value;

    public StringValueObject(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //允许子类复用equals
        if (!(o instanceof StringValueObject)) return false;
        StringValueObject that = (StringValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
