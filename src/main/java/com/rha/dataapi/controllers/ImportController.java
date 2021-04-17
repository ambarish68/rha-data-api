package com.rha.dataapi.controllers;

import com.rha.dataapi.services.ImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("import")
@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ImportController {

    private final ImportService importService;

    @PostMapping("/foodCounts")
    public ResponseEntity<String> importFoodCounts(@RequestParam("file") MultipartFile file) {
        log.info("importing food counts");
        Boolean isImportSuccessFul = importService.importFoodCounts(file);
        if (isImportSuccessFul) {
            return ResponseEntity.ok("imported data successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal server error");
    }
}
