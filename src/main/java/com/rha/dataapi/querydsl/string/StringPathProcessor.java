package com.rha.dataapi.querydsl.string;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.rha.dataapi.filters.SearchCriteria;
import com.rha.dataapi.filters.SearchOperation;
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
public class StringPathProcessor implements IDataTypePathProcessor {

    private final PathUtils pathUtils;

    @Override
    public void getPredicateFromSearchCriteria(PathBuilder entityPath, SearchCriteria criteria, @NotNull List<Predicate> predicates) {
        Pair<PathBuilder, String> stringPathBuilder = pathUtils.getPath(entityPath, criteria.getKey());
        StringPath stringPath = stringPathBuilder.getKey().getString(stringPathBuilder.getValue());
        if (SearchOperation.EQUAL.equals(criteria.getOperation())) {
            predicates.add(stringPath.eq(criteria.getValue().toString()));
        } else if (SearchOperation.MATCH.equals(criteria.getOperation())) {
            predicates.add(stringPath.containsIgnoreCase(criteria.getValue().toString()));
        }
    }
}
