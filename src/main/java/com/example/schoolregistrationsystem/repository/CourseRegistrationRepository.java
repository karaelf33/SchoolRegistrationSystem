package com.example.schoolregistrationsystem.repository;

import com.example.schoolregistrationsystem.model.CourseRegistration;
import com.example.schoolregistrationsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration,Integer> {

    List<CourseRegistration> findAllByStudent(Student student);
    void deleteByStudent_Id(Integer studentId);
    int  countByStudent(Student student);

    List<CourseRegistration> findByStudent_Id(Integer id);
}
