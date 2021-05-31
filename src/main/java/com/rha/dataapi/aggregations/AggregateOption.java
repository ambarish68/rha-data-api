package com.rha.dataapi.aggregations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregateOption {
    private String key;
    private AggregationOperation operation;
}
