package com.rha.dataapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rha.dataapi.hibernate.Zone;
import com.rha.dataapi.services.ZoneService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("zones")
@Log4j2
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<Zone>> getZones() {
        log.info("getting all zones");
        return ResponseEntity.ok(zoneService.getAllZones());
    }

    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zoneToBeCreated) {
        try {
            log.info("creating zone with parameters: " + objectMapper.writeValueAsString(zoneToBeCreated));
            return ResponseEntity.ok(zoneService.createZone(zoneToBeCreated));
        } catch (Exception e) {
            log.error("Error creating zone", e);
        }
        return (ResponseEntity<Zone>) ResponseEntity.badRequest();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Integer id, @RequestBody Zone zoneToBeUpdated) {
        try {
            if (zoneToBeUpdated.getId() != id) {
                zoneToBeUpdated.setId(id);
            }
            log.info("updating zone with parameters: " + objectMapper.writeValueAsString(zoneToBeUpdated));
            return ResponseEntity.ok(zoneService.updateZone(zoneToBeUpdated));
        } catch (Exception e) {
            log.error("Error creating zone", e);
        }
        return (ResponseEntity<Zone>) ResponseEntity.badRequest();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZone(@PathVariable Integer id) {
        try {
            log.info("updating zone with id: " + id);
            zoneService.deleteZone(id);
            return ResponseEntity.ok("Zone deactivated successfully");
        } catch (Exception e) {
            log.error("Error creating zone", e);
        }
        return (ResponseEntity<String>) ResponseEntity.badRequest();
    }
}
