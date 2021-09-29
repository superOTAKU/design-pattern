package org.otaku.school.course.domain;

import org.otaku.school.common.domain.AggregateRoot;
import org.otaku.school.common.domain.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 课程，课程对象是领域对象
 *
 * 领域对象现在不负责query，只负责具体的领域逻辑，在这里就是添加student，修改课程信息之类
 */
public class Course implements AggregateRoot, Entity {
    //课程id
    private Long id;
    //课程信息
    private CourseInformation information;
    //授课教师id
    private Teacher teacher;
    //参与此课程的学生
    private List<Student> students;

    //创建领域对象
    private Course(Long id, Teacher teacher, CourseInformation information, List<Student> students) {
        this.id = id;
        this.teacher = teacher;
        this.information = information;
        this.students = new ArrayList<>(students);
    }

    public static Course create(Long id, Teacher teacher, CourseInformation information, List<Student> students) {
        return new Course(id, teacher, information, students);
    }

    //依赖外部保证唯一
    public void setId(Long id) {
        this.id = id;
    }

    public void addStudent(Student student) {
        new CannotAddStudentAfterCourseBegin(information.getStartTime()).check();
        students.add(student);
    }

    //---------------------getters------------------------

    public Long getId() {
        return id;
    }

    public CourseInformation getInformation() {
        return information;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }
}
