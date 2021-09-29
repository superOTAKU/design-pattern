package org.otaku.school.course.domain;

public interface CourseRepository {

    void save(Course course);

    Course getById(Long id);

}
