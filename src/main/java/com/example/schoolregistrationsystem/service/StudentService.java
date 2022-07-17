package com.example.schoolregistrationsystem.service;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.model.Student;

public interface StudentService {

    GenericDto createStudent(RequestDto requestDto) throws CommonException;
    GenericDto deleteStudent(String code) throws CommonException;

    GenericDto getStudent(String code) throws CommonException;

    GenericDto getAllStudent();
     Student findStudent(String code) throws CommonException;
}
