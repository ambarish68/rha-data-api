package com.rha.dataapi.querydsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.rha.dataapi.filters.SearchCriteria;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IDataTypePathProcessor {

    void getPredicateFromSearchCriteria(PathBuilder entityPath, SearchCriteria criteria, @NotNull List<Predicate> predicates);
}
