package com.example.schoolregistrationsystem.service.impl;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.exception.ExceptionMessages;
import com.example.schoolregistrationsystem.model.Student;
import com.example.schoolregistrationsystem.repository.StudentRepository;
import com.example.schoolregistrationsystem.service.StudentService;
import com.example.schoolregistrationsystem.utils.MapperUtils;
import com.example.schoolregistrationsystem.utils.OperationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public GenericDto createStudent(RequestDto requestDto) {
        try {
            Student student = Student.builderStudent(requestDto);
            studentRepository.save(student);
            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(student),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("" + e);
            return OperationUtils.returnMessageHandling(
                    "Student code: " + requestDto.getStudentCode(),
                    OperationUtils.FAIL_CODE,
                    false,
                    ExceptionMessages.THIS_RECORD_IS_ALREADY_EXIST.getMessage());
        }
    }

    @Override
    public GenericDto deleteStudent(String code) throws CommonException {
        Optional<Student> studentIsExist = studentRepository.findByCode(code);
        if (studentIsExist.isEmpty()) {
            throw CommonException.notExistRecord();
        }
        studentRepository.delete(studentIsExist.get());

        return OperationUtils.returnMessageHandling(
                null,
                OperationUtils.SUCCESS_CODE,
                true,
                ExceptionMessages.THIS_REGISTRATION_ARE_DELETED.getMessage());


    }
}
