package com.example.schoolregistrationsystem.model;

import com.example.schoolregistrationsystem.dto.RequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COURSE")
@EqualsAndHashCode
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME", unique = true)
    private String name;

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Student> students = new HashSet<>();
    @Column(name = "CODE", unique = true)
    private String code;

    public static Course builderCourse(RequestDto req) {
        Course course = new Course();
        course.setName(req.getName());
        course.setCode(req.getCourseCode());
        return course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
