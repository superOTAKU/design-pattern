package org.otaku.school.common.domain;

public interface Specification {

    /**
     * 规约是否被满足
     */
    void check() throws SpecificationUnSatisfiedException;

}
