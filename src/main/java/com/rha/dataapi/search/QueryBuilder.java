package com.rha.dataapi.search;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rha.dataapi.hibernate.FoodCount;
import com.rha.dataapi.hibernate.QFoodCount;
import com.rha.dataapi.models.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QueryBuilder {

    private final EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }


    private final QueryDSLPredicateProcessor queryDSLPredicateProcessor;
    private final QueryDSLGroupByProcessor queryDSLGroupByProcessor;
    private final QueryDSLAggregateProcessor queryDSLAggregateProcessor;

    public void buildQuery(SearchRequest searchRequest) {
        JPAQuery jpaQuery = queryFactory
                .from(QFoodCount.foodCount)
                .where(queryDSLPredicateProcessor.getPredicate(searchRequest.getSearchCriteria(), FoodCount.class))
                .groupBy(queryDSLGroupByProcessor.getGroupingExpressions(searchRequest.getGroupByColumns(), searchRequest.getAggregateOptions(), FoodCount.class))
                .select();

    }
}
