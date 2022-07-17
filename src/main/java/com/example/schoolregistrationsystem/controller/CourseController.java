package com.example.schoolregistrationsystem.controller;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.service.CourseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {

   private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto createCourse(@RequestBody RequestDto requestDto){
        return courseService.createCourse(requestDto);
    }

    @DeleteMapping(value = "/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto deleteCourse(@PathVariable String code){
        return courseService.deleteCourse(code);
    }
    @PutMapping(value = "/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto registerCourse(@RequestBody RequestDto requestDto){
        return courseService.registerCourse(requestDto);
    }

    @GetMapping(value="{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto getCourse(@PathVariable String code) throws CommonException {
        return courseService.getCourse(code);
    }
    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDto getAllCourse()  {
        return courseService.getAllCourse();
    }
}
