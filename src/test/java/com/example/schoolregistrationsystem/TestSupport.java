package com.example.schoolregistrationsystem;

import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.model.Course;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    public static List<Course> generateCourses() {
        return IntStream.range(0, 5).mapToObj(i -> Course.builderCourse(new RequestDto(
                new Random(2).toString(),
                new Random(2).toString()))
        ).collect(Collectors.toList());
    }
}
