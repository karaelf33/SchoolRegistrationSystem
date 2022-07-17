package com.example.schoolregistrationsystem.service;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;

public interface StudentService {

    GenericDto createStudent(RequestDto requestDto);
    GenericDto deleteStudent(String code);
}
