package ru.alex.courseModel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String phone;
    private String email;

    @Column(name = "grade_book")
    private int gradeBook;

    @Column(name = "academic_performance")
    private float academicPerformance;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public void addStudentCourseGrade(StudentCourse studentCourse, Grade grade){
        studentCourse.getGrades().add(grade);
    }

    public void updateStudentCourseGrade(StudentCourse studentCourse, int id, Grade grade){
        studentCourse.getGrades().set(id, grade);
    }

    public void deleteStudentCourseGrade(StudentCourse studentCourse, int id){
        studentCourse.getGrades().remove(id);
    }

//------------------------------------------------------------------------------

    public StudentCourse getStudentCourse(StudentCourseId id){

        return studentCourses.stream()
                .filter(studentCourse -> studentCourse.getId().canEqual(id))
                .findFirst().get();
    }


    public void addCourse(Course course){
        StudentCourse studentCourse = new StudentCourse(this, course);
        studentCourse.setCourse(course);

        this.getStudentCourses().add(studentCourse);
        studentCourse.setStudent(this);
    }

    /**
    This method not needed. It is use for checks
     */
    public void removeCourse(StudentCourse studentCourse){
        this.getStudentCourses().remove(studentCourse);
        studentCourse.setStudent(null);
    }

}