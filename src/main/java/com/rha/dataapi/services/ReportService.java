package com.rha.dataapi.services;

import com.rha.dataapi.models.SearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReportService {

    private final FoodCountService foodCountService;

    public Object executeReportRequest(SearchRequest searchRequest){
        return foodCountService.getWithPredicate(searchRequest);
    }
}
