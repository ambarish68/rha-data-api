package com.rha.dataapi.controllers;

import com.rha.dataapi.hibernate.Status;
import com.rha.dataapi.services.StatusService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("statuses")
@Log4j2
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatuses() {
        log.info("getting all statuses");
        return ResponseEntity.ok(statusService.getAllStatuses());
    }
}
