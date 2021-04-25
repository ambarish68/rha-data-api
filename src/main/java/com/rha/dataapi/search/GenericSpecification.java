package com.rha.dataapi.search;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class GenericSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 1900581010229669687L;

    private List<SearchCriteria> list;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();
        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        getPath(criteria.getKey(),root), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        getPath(criteria.getKey(),root), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        getPath(criteria.getKey(),root), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(
                        getPath(criteria.getKey(),root), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        getPath(criteria.getKey(),root), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        getPath(criteria.getKey(), root), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(getPath(criteria.getKey(),root)),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(getPath(criteria.getKey(),root)),
                        criteria.getValue().toString().toLowerCase() + "%"));
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private Path getPath(String key, Root<T> root) {
        Preconditions.checkArgument(StringUtils.isNotBlank(key), "empty key can't have a path");
        Preconditions.checkNotNull(root, "can't build path for null root");
        if (!key.contains(".")) {
            return root.get(key);
        }
        Path path = null;
        while (key.contains(".")) {
            Integer firstIndexOfPeriod = key.indexOf(".");
            String firstKeyInPath = key.substring(0, firstIndexOfPeriod);
            if (Objects.isNull(path)) {
                path = root.get(firstKeyInPath);
            } else {
                path = path.get(firstKeyInPath);
            }
            key = key.substring(firstIndexOfPeriod + 1);
        }
        return path.get(key);
    }
}
