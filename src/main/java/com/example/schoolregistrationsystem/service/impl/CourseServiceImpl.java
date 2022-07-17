package com.example.schoolregistrationsystem.service.impl;


import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.model.Student;
import com.example.schoolregistrationsystem.repository.CourseRepository;
import com.example.schoolregistrationsystem.repository.StudentRepository;
import com.example.schoolregistrationsystem.service.CourseService;
import com.example.schoolregistrationsystem.utils.MapperUtils;
import com.example.schoolregistrationsystem.utils.OperationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public GenericDto createCourse(RequestDto requestDto) {
        Course course = null;
        try {
            Optional<Course> courseIsExist = courseRepository.findByCode(requestDto.getCourseCode());
            if (courseIsExist.isPresent()) {
                throw CommonException.existRecord();
            }
            course = Course.builderCourse(requestDto);
            courseRepository.save(course);

            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(course),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OperationUtils.returnMessageHandling(
                    course,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }
    }

    @Override
    public GenericDto deleteCourse(String code) {

        try {
            Optional<Course> courseIsExist = courseRepository.findByCode(code);
            if (courseIsExist.isEmpty()) {
                throw CommonException.notExistRecord();
            }
            courseRepository.deleteById(courseIsExist.get().getId());
            return OperationUtils.returnMessageHandling(
                    null,
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OperationUtils.returnMessageHandling(
                    null,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }
    }

    @Override
    public GenericDto registerCourse(RequestDto req) {
        try {
            Optional<Student> student = studentRepository.findByCode(req.getStudentCode());
            Optional<Course> course = courseRepository.findByCode(req.getCourseCode());
            if (student.isEmpty() || course.isEmpty()) {
                throw CommonException.notExistRecord();
            }


            student.get().registerCourse(course.get());
            studentRepository.save(student.get());

            return OperationUtils.returnMessageHandling(
                    "Student Code" + student.get().getCode(),
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
