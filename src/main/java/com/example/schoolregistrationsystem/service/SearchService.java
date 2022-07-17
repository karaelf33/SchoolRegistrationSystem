package com.example.schoolregistrationsystem.service;

import com.example.schoolregistrationsystem.dto.GenericDto;

public interface SearchService {
    GenericDto search(String code,String codeType);
}
