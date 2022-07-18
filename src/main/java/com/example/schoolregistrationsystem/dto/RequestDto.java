package com.example.schoolregistrationsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDto {

    private String name;
    private String surname;
    private String studentCode;
    private String courseCode;

    public RequestDto(String name, String surname, String studentCode, String courseCode) {
        this.name = name;
        this.surname = surname;
        this.studentCode = studentCode;
        this.courseCode = courseCode;
    }

    public RequestDto(String name,String courseCode) {
        this.name = name;
        this.courseCode=courseCode;
    }
}
