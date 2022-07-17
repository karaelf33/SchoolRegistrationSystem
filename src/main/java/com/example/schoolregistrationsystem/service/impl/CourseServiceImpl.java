package com.example.schoolregistrationsystem.service.impl;


import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.model.Student;
import com.example.schoolregistrationsystem.repository.CourseRepository;
import com.example.schoolregistrationsystem.repository.StudentRepository;
import com.example.schoolregistrationsystem.service.CourseService;
import com.example.schoolregistrationsystem.service.StudentService;
import com.example.schoolregistrationsystem.utils.MapperUtils;
import com.example.schoolregistrationsystem.utils.OperationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentRepository studentRepository,
                             StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
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
            Course courseIsExist = findCourse(code);
            courseRepository.deleteById(courseIsExist.getId());
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
            Student student = studentService.findStudent(req.getStudentCode());
            Course course = findCourse(req.getCourseCode());

            student.registerCourse(course);
            studentRepository.save(student);

            return OperationUtils.returnMessageHandling(
                    "Student Code: " + student.getCode(),
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

    @Override
    public GenericDto getCourse(String code) throws CommonException {
        Course course = findCourse(code);
        return OperationUtils.returnMessageHandling(
                course,
                OperationUtils.SUCCESS_CODE,
                true,
                OperationUtils.SUCCESS_MESSAGE);
    }

    @Override
    public GenericDto getAllCourse() {
        List<Course> courseList = courseRepository.findAll();
        return OperationUtils.returnMessageHandling(
                courseList,
                OperationUtils.SUCCESS_CODE,
                true,
                OperationUtils.SUCCESS_MESSAGE
        );
    }

    public Course findCourse(String code) throws CommonException {
        Optional<Course> course = courseRepository.findByCode(code);
        if (course.isEmpty()) {
            throw CommonException.notExistRecord();
        }
        return course.get();
    }
}
