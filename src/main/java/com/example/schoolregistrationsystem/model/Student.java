package com.example.schoolregistrationsystem.model;

import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENT")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "CODE",unique=true)
    private String code;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.LAZY)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Course> courses = new HashSet<>();

    public static Student builderStudent(RequestDto req) {
        Student student = new Student();
        student.setName(req.getName());
        student.setSurname(req.getSurname());
        student.setCode(req.getStudentCode());
        return student;
    }

    public void registerCourse(Course course) throws CommonException {
        if (courses.contains(course)) {
            throw new CommonException("This student has already taken this course");
        }
        if (course.getStudents().size() == 50 || this.getCourses().size() == 5) {
            throw new CommonException("Course Capacity=" + course.getStudents().size()
                    + "Number of courses taken by the student=" + this.getCourses().size());
        }
        courses.add(course);
        course.getStudents().add(this);
    }

}
