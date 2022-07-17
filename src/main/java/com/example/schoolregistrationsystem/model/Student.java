package com.example.schoolregistrationsystem.model;

import com.example.schoolregistrationsystem.dto.RequestDto;
import lombok.Data;

import javax.persistence.*;
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
    @Column(name = "CODE")
    private String code;
    @Column(name = "CAPACITY")
    private int capacity;

    @ManyToMany(mappedBy = "students")
    Set<Course> registrations;

    public static Student builderStudent(RequestDto req) {
        Student student = new Student();
        student.setName(req.getName());
        student.setSurname(req.getSurname());
        student.setCode(req.getStudentCode());
        return student;
    }

}
