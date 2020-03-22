package com.rha.dataapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rha.dataapi.hibernate.Relation;
import com.rha.dataapi.services.RelationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("relations")
@Log4j2
public class RelationController {

    @Autowired
    private RelationService relationService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<Relation>> getAllRelations() {
        return ResponseEntity.ok(relationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relation> getRelation(@PathVariable Integer id) {
        log.info("getting all cities");
        return ResponseEntity.ok(relationService.get(id));
    }

    @PostMapping
    public ResponseEntity<Relation> createRelation(@RequestBody Relation relation) {
        try {
            log.info("creating relation with parameters: " + objectMapper.writeValueAsString(relation));
        } catch (Exception e) {
            log.error("Error creating relation", e);
        }
        return ResponseEntity.ok(relationService.create(relation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Relation> updateRelation(@PathVariable Integer id, @RequestBody Relation city) {
        try {
            if (!id.equals(city.getId())) {
                city.setId(id);
            }
            log.info("updating city with parameters: " + objectMapper.writeValueAsString(city));
            return ResponseEntity.ok(relationService.update(id, city));
        } catch (Exception e) {
            log.error("Error updating city ", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRelation(@PathVariable Integer id) {
        try {
            log.info("deleting relation with id: " + id);
            relationService.delete(id);
            return ResponseEntity.ok("relation deactivated successfully");
        } catch (Exception e) {
            log.error("Error deleting relation", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
