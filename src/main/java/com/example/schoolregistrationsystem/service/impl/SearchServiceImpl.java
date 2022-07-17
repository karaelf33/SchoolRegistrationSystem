package com.example.schoolregistrationsystem.service.impl;

import com.example.schoolregistrationsystem.utils.SearchCriteria;
import com.example.schoolregistrationsystem.dto.GenericDto;
import com.example.schoolregistrationsystem.exception.ExceptionMessages;
import com.example.schoolregistrationsystem.model.Course;
import com.example.schoolregistrationsystem.model.Student;
import com.example.schoolregistrationsystem.service.SearchService;
import com.example.schoolregistrationsystem.utils.OperationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    public List<Student> findByCourseNames(String code) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();

        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Student> subqueryStudent = subquery.from(Student.class);
        Join<Course, Student> subqueryCourse = subqueryStudent.join("courses");

        subquery.select(subqueryStudent.get("id")).where(cb.equal(subqueryCourse.get("code"), code));

        predicates.add(cb.in(student.get("id")).value(subquery));

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Student> query = entityManager.createQuery(cq);

        return query.getResultList();
    }
}
