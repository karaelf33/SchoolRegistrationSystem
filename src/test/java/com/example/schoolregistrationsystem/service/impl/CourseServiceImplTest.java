package com.example.schoolregistrationsystem.service.impl;

import com.example.schoolregistrationsystem.TestSupport;
import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.repository.CourseRepository;
import com.example.schoolregistrationsystem.repository.StudentRepository;
import com.example.schoolregistrationsystem.service.StudentService;
import com.example.schoolregistrationsystem.utils.OperationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseServiceImplTest extends TestSupport {
    private CourseServiceImpl courseService;
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = Mockito.mock(CourseRepository.class);
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        StudentService studentService = Mockito.mock(StudentService.class);
        courseService = new CourseServiceImpl(courseRepository,
                studentRepository,
                studentService);
    }

    @Test
    public void whenCreateCourseCalledWithRequestDto_itShouldReturnGenericDto() {
        RequestDto requestDto = new RequestDto();
        requestDto.setName("JAVA course");
        requestDto.setCourseCode("JAVA17_RTA");


        Optional<Course> courseIsExist = courseRepository.findByCode(requestDto.getCourseCode());
        Course course = Course.builderCourse(requestDto);


        Mockito.when(courseRepository.findByCode(requestDto.getCourseCode())).thenReturn(courseIsExist);
        // TODO apply throw scenario
        Mockito.when(courseRepository.save(course)).thenReturn(course);

        GenericDto actual = courseService.createCourse(requestDto);
        GenericDto expected = OperationUtils.returnMessageHandling(
                course,
                OperationUtils.SUCCESS_CODE,
                true,
                OperationUtils.SUCCESS_MESSAGE);

        assertEquals(expected, actual);
       // Mockito.verify(courseRepository).findByCode(requestDto.getCourseCode());
        Mockito.verify(courseRepository).save(course);
    }

    @Test
    public void whenRegisterCourseCalledWithRequestDto_itShouldReturnGenericDto() {

    }

    @Test
    public void findCourseWithCodeAndReturnCourse() {
        String code = "1234";
        Course course = new Course();

        Mockito.when(courseRepository.findByCode(code)).thenReturn(Optional.of(course));
    }

    @Test
    public void deleteCourseWithCodeThenReturnGenericDto() {

    }

    @Test
    public void testGetAllCourses_itShouldReturnGenericDto() {

        List<Course> courseList = generateCourses();
        Mockito.when(courseRepository.findAll()).thenReturn(courseList);

        GenericDto actual = courseService.getAllCourses();

        GenericDto expected = OperationUtils.returnMessageHandling(
                courseList,
                OperationUtils.SUCCESS_CODE,
                true,
                OperationUtils.SUCCESS_MESSAGE
        );

        assertEquals(expected, actual);
        Mockito.verify(courseRepository).findAll();


    }
}