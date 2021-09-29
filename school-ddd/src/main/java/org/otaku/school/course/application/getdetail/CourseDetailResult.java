package org.otaku.school.course.application.getdetail;

import lombok.Data;
import org.otaku.school.common.domain.cqrs.Result;
import org.otaku.school.course.domain.Course;
import org.otaku.school.course.domain.CourseInformation;
import org.otaku.school.course.domain.Student;
import org.otaku.school.course.domain.Teacher;

import java.util.List;

@Data
public class CourseDetailResult implements Result {
    //课程id
    private Long id;
    //课程信息
    private CourseInformation information;
    //授课教师id
    private Teacher teacher;
    //参与此课程的学生
    private List<Student> students;

    public static CourseDetailResult create(Course course) {
        CourseDetailResult result = new CourseDetailResult();
        result.id = course.getId();
        result.information = course.getInformation();
        result.teacher = course.getTeacher();
        result.students = course.getStudents();
        return result;
    }

}
