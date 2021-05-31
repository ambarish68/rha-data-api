package com.rha.dataapi.search;

import com.querydsl.core.group.GroupExpression;
import com.rha.dataapi.aggregations.AggregateOption;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryDSLAggregateProcessor {

    public void processAggregations(List<String> groupByColumns, List<String> displayColumns, List<AggregateOption> aggregations, Class clazz) {

    }
}
