package com.example.schoolregistrationsystem.repository;

import com.example.schoolregistrationsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Optional<Student> findByCode(String code);
}
