package com.rha.dataapi.querydsl.numeric;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.rha.dataapi.filters.SearchCriteria;
import com.rha.dataapi.querydsl.IDataTypePathProcessor;
import com.rha.dataapi.querydsl.PathUtils;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NumericPathProcessor implements IDataTypePathProcessor {

    private final PathUtils pathUtils;

    @Override
    public void getPredicateFromSearchCriteria(PathBuilder entityPath, SearchCriteria criteria, @NotNull List<Predicate> predicates) {
        Pair<PathBuilder, String> numericPathBuilder = pathUtils.getPath(entityPath, criteria.getKey());
        NumberPath path = numericPathBuilder.getKey().getNumber(numericPathBuilder.getValue(), Long.class);
        Long value = Long.parseLong(criteria.getValue().toString());
        switch (criteria.getOperation()) {
            case EQUAL:
                predicates.add(path.eq(value));
            case NOT_EQUAL:
                predicates.add(path.ne(value));
            case GREATER_THAN:
                predicates.add(path.gt(value));
            case GREATER_THAN_EQUAL:
                predicates.add(path.goe(value));
            case LESS_THAN:
                predicates.add(path.lt(value));
            case LESS_THAN_EQUAL:
                predicates.add(path.loe(value));
        }
    }
}
