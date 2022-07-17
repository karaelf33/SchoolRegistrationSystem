package com.example.schoolregistrationsystem.controller;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.model.Student;
import com.example.schoolregistrationsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = "/avc", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Course> search() {
        Optional<Student> student = studentRepository.findByCode("s3");
        if (student.isEmpty()) {
            return null;
        }
        Set<Course> courses=student.get().getCourses();
        System.out.println(courses);
        return courses ;
    }

}
