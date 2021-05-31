package com.rha.dataapi.search;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.rha.dataapi.aggregations.AggregateOption;
import com.rha.dataapi.aggregations.AggregationOperation;
import com.rha.dataapi.querydsl.PathUtils;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QueryDSLGroupByProcessor {

    private final PathUtils pathUtils;

    public Expression[] getGroupingExpressions(List<String> groupByColumns, List<AggregateOption> aggregateColumns, Class clazz) {
        PathBuilder entityPath = new PathBuilder<>(clazz.getClass(), "entity");
        List<Expression> groupExpression = new ArrayList<>();
        for (AggregateOption aggregateColumn : aggregateColumns) {
            groupExpression.add(getExpression(aggregateColumn.getOperation(), pathUtils.getPath(entityPath, aggregateColumn.getKey())));
        }
        return (Expression[]) groupExpression.toArray();
    }

    private Expression getExpression(AggregationOperation aggregationOperation, Pair<PathBuilder, String> path) {
        switch (aggregationOperation) {
            case SUM:
                return GroupBy.sum(path.getKey());
            case MINIMUM:
                return GroupBy.min(path.getKey());
            case MAXIMUM:
                return GroupBy.max(path.getKey());
            case AVERAGE:
                return GroupBy.avg(path.getKey());
            default:
                return null;
        }
    }
}
