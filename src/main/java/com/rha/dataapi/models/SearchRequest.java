package com.rha.dataapi.models;

import com.rha.dataapi.filters.SearchCriteria;
import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private List<SearchCriteria> searchCriteria;
    private List<String> displayColumns;
    private List<String> groupByColumns;
    private List<String> aggregateOverColumns;
    private String collection;
}
