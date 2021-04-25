package com.rha.dataapi.controllers;

import com.rha.dataapi.models.SearchRequest;
import com.rha.dataapi.services.ReportService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reports")
@Log4j2
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("")
    public ResponseEntity<Object> getCity(@RequestBody SearchRequest searchRequest) {
        log.info("executing report");
        return ResponseEntity.ok().body(reportService.executeReportRequest(searchRequest));
    }
}
