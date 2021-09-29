package org.otaku.school.common.domain;

public class SpecificationUnSatisfiedException extends RuntimeException {

    public SpecificationUnSatisfiedException() {
    }

    public SpecificationUnSatisfiedException(String message) {
        super(message);
    }

    public SpecificationUnSatisfiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpecificationUnSatisfiedException(Throwable cause) {
        super(cause);
    }

    public SpecificationUnSatisfiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
