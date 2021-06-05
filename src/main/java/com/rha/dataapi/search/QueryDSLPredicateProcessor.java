package com.rha.dataapi.search;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.rha.dataapi.aggregations.AggregateOption;
import com.rha.dataapi.filters.SearchCriteria;
import com.rha.dataapi.querydsl.IDataTypePathProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QueryDSLPredicateProcessor {

    private final IDataTypePathProcessor booleanPathProcessor;
    private final IDataTypePathProcessor numericPathProcessor;
    private final IDataTypePathProcessor stringPathProcessor;

    public BooleanExpression getPredicate(List<SearchCriteria> listOfCriteria, Class clazz) {
        PathBuilder entityPath = new PathBuilder<>(clazz.getClass(), "entity");
        List<Predicate> predicates = new ArrayList<>();
        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (SearchCriteria criteria : listOfCriteria) {
            if (StringUtils.isNumeric(criteria.getValue().toString())) {
                numericPathProcessor.getPredicateFromSearchCriteria(entityPath, criteria, predicates);
            } else if (Boolean.parseBoolean(criteria.getValue().toString())) {
                booleanPathProcessor.getPredicateFromSearchCriteria(entityPath, criteria, predicates);
            } else {
                stringPathProcessor.getPredicateFromSearchCriteria(entityPath, criteria, predicates);
            }
        }
        for (com.querydsl.core.types.Predicate predicate : predicates) {
            result = result.and(predicate);
        }
        return result;
    }
}
