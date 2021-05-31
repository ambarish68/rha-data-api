package com.rha.dataapi.querydsl;

import com.google.common.base.Preconditions;
import com.querydsl.core.types.dsl.PathBuilder;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PathUtils {

    public Pair<PathBuilder, String> getPath(PathBuilder entityPath, String key) {
        Preconditions.checkArgument(StringUtils.isNotBlank(key), "empty key can't have a path");
        Preconditions.checkNotNull(entityPath, "can't build path for null entityPath");
        if (!key.contains(".")) {
            return new Pair(entityPath, key);
        }
        PathBuilder path = entityPath;
        while (key.contains(".")) {
            Integer firstIndexOfPeriod = key.indexOf(".");
            String firstKeyInPath = key.substring(0, firstIndexOfPeriod);
            path = entityPath.get(firstKeyInPath);
            key = key.substring(firstIndexOfPeriod + 1);
        }
        return new Pair(path, key);
    }
}
