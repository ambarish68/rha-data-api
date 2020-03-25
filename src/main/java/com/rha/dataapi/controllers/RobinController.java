package com.rha.dataapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rha.dataapi.hibernate.Robin;
import com.rha.dataapi.services.RobinService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("robins")
@Log4j2
public class RobinController {

    @Autowired
    private RobinService robinService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<Robin>> getAllRobins() {
        return ResponseEntity.ok(robinService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Robin> getRobin(@PathVariable Integer id) {
        log.info("getting all robins");
        return ResponseEntity.ok(robinService.get(id));
    }

    @PostMapping
    public ResponseEntity<Robin> createRobin(@RequestBody Robin robin) {
        try {
            log.info("creating robin with parameters: " + objectMapper.writeValueAsString(robin));
        } catch (Exception e) {
            log.error("Error creating city", e);
        }
        return ResponseEntity.ok(robinService.create(robin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Robin> updateRobin(@PathVariable Integer id, @RequestBody Robin robin) {
        try {
            if (!id.equals(robin.getId())) {
                robin.setId(id);
            }
            log.info("updating city with parameters: " + objectMapper.writeValueAsString(robin));
            return ResponseEntity.ok(robinService.update(id, robin));
        } catch (Exception e) {
            log.error("Error updating city ", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRobin(@PathVariable Integer id) {
        try {
            log.info("deleting robin with id: " + id);
            robinService.delete(id);
            return ResponseEntity.ok("robin deactivated successfully");
        } catch (Exception e) {
            log.error("Error deleting city", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
