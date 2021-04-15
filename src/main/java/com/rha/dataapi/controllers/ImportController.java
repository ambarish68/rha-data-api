package com.rha.dataapi.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("import")
@Log4j2
public class ImportController {

    @PostMapping("/foodCounts")
    public ResponseEntity<String> getCity(@RequestParam("file") MultipartFile file) {
        log.info("importing food counts");
        return ResponseEntity.ok("imported data successfully");
    }
}
