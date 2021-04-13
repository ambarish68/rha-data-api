package com.rha.dataapi.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("lookup")
@Log4j2
public class LookupController {

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllLookups() {
        //return all statuses
        return null;
    }
}
