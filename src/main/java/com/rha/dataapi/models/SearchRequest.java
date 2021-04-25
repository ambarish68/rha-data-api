package com.rha.dataapi.models;

import com.rha.dataapi.search.SearchCriteria;
import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private List<SearchCriteria> searchCriteria;
    private List<String> displayColumns;
    private List<String> aggregateOverColumns;
    private String collection;
}
