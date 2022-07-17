package com.example.schoolregistrationsystem.service.impl;

import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.exception.ExceptionMessages;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.model.Student;
import com.example.schoolregistrationsystem.service.SearchService;
import com.example.schoolregistrationsystem.utils.OperationUtils;
import com.example.schoolregistrationsystem.utils.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    private final EntityManager entityManager;

    public SearchServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public GenericDto search(String code, String codeType) {
        try {
            if (codeType.equalsIgnoreCase("courses")) {
                SearchCriteria<Student, Course> searchCriteria = new SearchCriteria<>(Student.class);
                List<Student> students = searchCriteria.searchByCode(entityManager, code, codeType);
                return OperationUtils.returnMessageHandling(
                        students,
                        OperationUtils.SUCCESS_CODE,
                        true,
                        OperationUtils.SUCCESS_MESSAGE);
            }
            SearchCriteria<Course, Student> searchCriteria = new SearchCriteria<>(Course.class);
            List<Course> courses = searchCriteria.searchByCode(entityManager, code, codeType);
            return OperationUtils.returnMessageHandling(
                    courses,
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("" + e);
            return OperationUtils.returnMessageHandling(
                    ExceptionMessages.THIS_RECORD_IS_NOT_ALREADY_EXIST,
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        }


    }
}
