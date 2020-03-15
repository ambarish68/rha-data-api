package com.rha.dataapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rha.dataapi.hibernate.City;
import com.rha.dataapi.services.CityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("cities")
@Log4j2
public class CityController {

    @Autowired
    private CityService cityService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable Integer id) {
        log.info("getting all cities");
        return ResponseEntity.ok(cityService.get(id));
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        try {
            log.info("creating city with parameters: " + objectMapper.writeValueAsString(city));
        } catch (Exception e) {
            log.error("Error creating city", e);
        }
        return ResponseEntity.ok(cityService.create(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Integer id, @RequestBody City city) {
        try {
            if (!id.equals(city.getId())) {
                city.setId(id);
            }
            log.info("updating city with parameters: " + objectMapper.writeValueAsString(city));
            return ResponseEntity.ok(cityService.update(id, city));
        } catch (Exception e) {
            log.error("Error updating city ", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Integer id) {
        try {
            log.info("deleting city with id: " + id);
            cityService.delete(id);
            return ResponseEntity.ok("City deactivated successfully");
        } catch (Exception e) {
            log.error("Error deleting city", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
