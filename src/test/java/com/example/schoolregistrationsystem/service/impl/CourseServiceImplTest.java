package com.example.schoolregistrationsystem.service.impl;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.dto.RequestDto;
import com.example.schoolregistrationsystem.exception.CommonException;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.repository.CourseRepository;
import com.example.schoolregistrationsystem.repository.StudentRepository;
import com.example.schoolregistrationsystem.service.StudentService;
import com.example.schoolregistrationsystem.utils.MapperUtils;
import com.example.schoolregistrationsystem.utils.OperationUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;

public class CourseServiceImplTest {

    private CourseServiceImpl courseService;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private StudentService studentService;
    @Before
    public void setUp() throws Exception {
        courseRepository= Mockito.mock(CourseRepository.class);
        studentRepository= Mockito.mock(StudentRepository.class);
        studentService= Mockito.mock(StudentService.class);
        courseService=new CourseServiceImpl(courseRepository,
                studentRepository,
                studentService);
    }

    @Test
    public void whenCreateCourseCalledWithRequestDto_itShouldReturnGenericDto(){
        RequestDto requestDto=new RequestDto();
        requestDto.setName("JAVA course");
        requestDto.setCourseCode("JAVA17_RTA");



        Optional<Course> courseIsExist=courseRepository.findByCode(requestDto.getCourseCode());
        Course course=Course.builderCourse(requestDto);
        GenericDto genericDto=new GenericDto();
        genericDto.setResultData(MapperUtils.entityToHashMapMapper(course));
        genericDto.setResultCode(OperationUtils.SUCCESS_CODE);
        genericDto.setResultFlag(true);
        genericDto.setResultMessage(OperationUtils.SUCCESS_MESSAGE);


        Mockito.when(courseRepository.findByCode(requestDto.getCourseCode())).thenReturn(courseIsExist);
      // TODO apply throw scenario
        Mockito.when(courseRepository.save(course)).thenReturn(course);

        GenericDto result= courseService.createCourse(requestDto);
        Assert.assertEquals(result,genericDto);
        Mockito.verify(courseRepository).findByCode(requestDto.getCourseCode());
      //  Mockito.verify(courseRepository).save(course);
    }

    @Test
    public void   whenRegisterCourseCalledWithRequestDto_itShouldReturnGenericDto(){

    }

    @Test
    public void findCourseWithCodeAndReturnCourse() {
        String code="1234";
        Course course=new Course();

        Mockito.when(courseRepository.findByCode(code)).thenReturn(Optional.of(course));
    }

    @Test
    public void deleteCourseWithCodeThenReturnGenericDto(){

    }

}