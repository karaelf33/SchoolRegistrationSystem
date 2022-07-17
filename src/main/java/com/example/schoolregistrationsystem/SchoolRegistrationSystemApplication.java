package com.example.schoolregistrationsystem;

import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.model.Student;
import com.example.schoolregistrationsystem.repository.CourseRepository;
import com.example.schoolregistrationsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SchoolRegistrationSystemApplication implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;


    public static void main(String[] args) {
        SpringApplication.run(SchoolRegistrationSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {

        RequestDto s1 = new RequestDto("John", "Dan", "s1", "");
        RequestDto s2 = new RequestDto("Juliee", "Dar", "s2", "");
        RequestDto s3 = new RequestDto("Dasha", "Leso", "s3", "");
        RequestDto c1 = new RequestDto("JAVA", "", "", "c1");
        RequestDto c2 = new RequestDto("C#", "", "", "c2");
        RequestDto c3 = new RequestDto("PYTHON#", "", "", "c3");
        List<Student> sList = new ArrayList<>();
        sList.add(Student.builderStudent(s1));
        sList.add(Student.builderStudent(s2));
        sList.add(Student.builderStudent(s3));

        List<Course> cList = new ArrayList<>();
        cList.add(Course.builderCourse(c1));
        cList.add(Course.builderCourse(c2));
        cList.add(Course.builderCourse(c3));

        studentRepository.saveAll(sList);
        courseRepository.saveAll(cList);
    }

}
