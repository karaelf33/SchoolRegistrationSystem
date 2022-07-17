package com.example.schoolregistrationsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "COURSE_REGISTRATOPON")
@Data
public class CourseRegistration {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public static CourseRegistration add(Student student, Course course) {
        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setCourse(course);
        courseRegistration.setStudent(student);
        return courseRegistration;
    }

}
