package com.example.schoolregistrationsystem.utils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class SearchCriteria<PK, SK> {

    private final Class<PK> primaryKeyClass;

    public SearchCriteria(Class<PK> primaryKeyClass) {
        super();
        this.primaryKeyClass = primaryKeyClass;
    }

    public List<PK> searchByCode(EntityManager entityManager, String code, String type) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PK> cq = cb.createQuery(primaryKeyClass);
        Root<PK> student = cq.from(primaryKeyClass);
        List<Predicate> predicates = new ArrayList<>();

        Subquery<Long> subQuery = cq.subquery(Long.class);
        Root<PK> primaryClass = subQuery.from(primaryKeyClass);
        Join<SK, PK> secondaryClass = primaryClass.join(type);

        subQuery.select(primaryClass.get("id")).where(cb.equal(secondaryClass.get("code"), code));

        predicates.add(cb.in(student.get("id")).value(subQuery));

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<PK> query = entityManager.createQuery(cq);

        return query.getResultList();
    }


}
