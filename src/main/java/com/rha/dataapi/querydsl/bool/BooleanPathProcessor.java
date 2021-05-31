package com.rha.dataapi.querydsl.bool;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanPath;
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
public class BooleanPathProcessor implements IDataTypePathProcessor {

    private final PathUtils pathUtils;

    @Override
    public void getPredicateFromSearchCriteria(PathBuilder entityPath, SearchCriteria criteria, @NotNull List<Predicate> predicates) {
        Pair<PathBuilder, String> booleanPathBuilder = pathUtils.getPath(entityPath, criteria.getKey());
        BooleanPath booleanPath = booleanPathBuilder.getKey().getBoolean(booleanPathBuilder.getValue());
        switch (criteria.getOperation()) {
            case EQUAL:
                predicates.add(booleanPath.eq(Boolean.parseBoolean(criteria.getValue().toString())));
            case NOT_EQUAL:
                predicates.add(booleanPath.ne(Boolean.parseBoolean(criteria.getValue().toString())));
        }
    }
}
