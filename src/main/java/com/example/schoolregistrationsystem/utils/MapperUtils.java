package com.example.schoolregistrationsystem.utils;

import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.model.Student;

import java.util.HashMap;
import java.util.Map;

public class MapperUtils {

    private MapperUtils(){}

    public static Map<String, String> entityToHashMapMapper(Student student) {

        HashMap<String, String> resultHashmap = new HashMap<>();
        resultHashmap.put("id", String.valueOf(student.getId()));
        resultHashmap.put("name", student.getName());
        resultHashmap.put("surname", student.getSurname());
        resultHashmap.put("code", student.getCode());

        return resultHashmap;
    }
    public static Map<String, String> entityToHashMapMapper(Course course) {

        HashMap<String, String> resultHashmap = new HashMap<>();
        resultHashmap.put("id", String.valueOf(course.getId()));
        resultHashmap.put("name", course.getName());
        resultHashmap.put("code", course.getCode());

        return resultHashmap;
    }

}
