package com.rha.dataapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rha.dataapi.hibernate.Zone;
import com.rha.dataapi.services.ZoneService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(zoneService.getAll());
    }

    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zoneToBeCreated) {
        try {
            log.info("creating zone with parameters: " + objectMapper.writeValueAsString(zoneToBeCreated));
            return ResponseEntity.ok(zoneService.create(zoneToBeCreated));
        } catch (Exception e) {
            log.error("Error creating zone", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Integer id, @RequestBody Zone zoneToBeUpdated) {
        try {
            if (!id.equals(zoneToBeUpdated.getId())) {
                zoneToBeUpdated.setId(id);
            }
            log.info("updating zone with parameters: " + objectMapper.writeValueAsString(zoneToBeUpdated));
            return ResponseEntity.ok(zoneService.update(id, zoneToBeUpdated));
        } catch (Exception e) {
            log.error("Error creating zone", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZone(@PathVariable Integer id) {
        try {
            log.info("deleting zone with id: " + id);
            zoneService.delete(id);
            return ResponseEntity.ok("Zone deactivated successfully");
        } catch (Exception e) {
            log.error("Error creating zone", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
