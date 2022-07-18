package com.example.schoolregistrationsystem.service;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;

public interface CourseService {

    GenericDto createCourse(RequestDto requestDto);
    GenericDto deleteCourse(String code);
    GenericDto registerCourse(RequestDto req);
    GenericDto getCourse(String code) throws CommonException;

    GenericDto getAllCourses();
}
