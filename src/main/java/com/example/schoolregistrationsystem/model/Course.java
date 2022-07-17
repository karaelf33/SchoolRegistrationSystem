package com.example.schoolregistrationsystem.model;

import com.example.schoolregistrationsystem.dto.RequestDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COURSE")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;
    @Column(name = "CAPACITY")
    private int capacity;
    @ManyToMany
    Set<Student> students;

    public static Course builderCourse(RequestDto req) {
        Course course=new Course();
        course.setName(req.getName());
        course.setCode(req.getCourseCode());
        return course;
    }
}
