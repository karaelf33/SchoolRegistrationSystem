package com.example.schoolregistrationsystem.repository;

import com.example.schoolregistrationsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Optional<Course> findByCode(String code);
}
