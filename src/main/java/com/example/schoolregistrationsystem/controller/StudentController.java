package com.example.schoolregistrationsystem.controller;


import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {


    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto createStudent(@RequestBody RequestDto requestDto){
        return studentService.createStudent(requestDto);
    }

    @DeleteMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto deleteStudent(@RequestParam String code){
        return studentService.deleteStudent(code);
    }

}
