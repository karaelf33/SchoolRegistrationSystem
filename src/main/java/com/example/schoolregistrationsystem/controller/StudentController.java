package com.example.schoolregistrationsystem.controller;


import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto createStudent(@RequestBody RequestDto requestDto) throws CommonException {
        return studentService.createStudent(requestDto);
    }

    @DeleteMapping(value = "{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto deleteStudent(@PathVariable String code) throws CommonException {
        return studentService.deleteStudent(code);
    }

    @GetMapping(value = "{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto getStudent(@PathVariable String code) throws CommonException {
        return studentService.getStudent(code);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto getAllStudent() {
        return studentService.getAllStudent();
    }

}
