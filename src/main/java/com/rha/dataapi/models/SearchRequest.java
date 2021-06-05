package com.rha.dataapi.models;

import com.rha.dataapi.aggregations.AggregateOption;
import com.rha.dataapi.filters.SearchCriteria;
import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private List<SearchCriteria> searchCriteria;
    private List<String> displayColumns;
    private List<String> groupByColumns;
    private List<AggregateOption> aggregateOptions;
    private String collection;
}
