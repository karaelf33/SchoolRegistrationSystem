package com.example.schoolregistrationsystem.service.impl;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.model.CourseRegistration;
import com.example.schoolregistrationsystem.model.Student;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.repository.CourseRegistrationRepository;
import com.example.schoolregistrationsystem.repository.CourseRepository;
import com.example.schoolregistrationsystem.repository.StudentRepository;
import com.example.schoolregistrationsystem.service.StudentService;
import com.example.schoolregistrationsystem.utils.MapperUtils;
import com.example.schoolregistrationsystem.utils.OperationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    StudentRepository studentRepository;
    CourseRegistrationRepository courseRegistrationRepository;
    CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseRegistrationRepository courseRegistrationRepository,
                              CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public GenericDto createStudent(RequestDto requestDto) {
        Student student = null;
        try {
            Optional<Student> studentIsExist = studentRepository.findByCode(requestDto.getStudentCode());
            if (studentIsExist.isPresent()) {
                throw CommonException.existRecord();
            }
            student = Student.builderStudent(requestDto);
            studentRepository.save(student);
            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(student),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return OperationUtils.returnMessageHandling(
                    student,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());

        }
    }

    @Override
    public GenericDto deleteStudent(String code) {
        Optional<Student> studentIsExist = studentRepository.findByCode(code);
        try {
            if (studentIsExist.isEmpty()) {
                throw CommonException.notExistRecord();
            }
            List<CourseRegistration> courseRegistrationList = courseRegistrationRepository.findByStudent_Id(studentIsExist.get().getId());

            for (CourseRegistration courseRegistration : courseRegistrationList) {
                Optional<Course> course = courseRepository.findByCode(courseRegistration.getCourse().getCode());
                course.orElseThrow().setCapacity(course.get().getCapacity() - 1);
                courseRepository.save(course.get());
                courseRegistrationRepository.deleteById(courseRegistration.getId());
            }


            courseRegistrationRepository.deleteByStudent_Id(studentIsExist.get().getId());
            studentRepository.save(studentIsExist.get());

            return OperationUtils.returnMessageHandling(
                    null,
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("" + e);
            return OperationUtils.returnMessageHandling(
                    null,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }


    }
}
