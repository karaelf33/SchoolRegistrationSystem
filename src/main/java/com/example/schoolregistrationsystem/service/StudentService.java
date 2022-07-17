package com.example.schoolregistrationsystem.service;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;

public interface StudentService {

    GenericDto createStudent(RequestDto requestDto) throws CommonException;
    GenericDto deleteStudent(String code) throws CommonException;
}
