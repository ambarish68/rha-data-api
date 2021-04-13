package com.rha.dataapi.controllers;

import com.rha.dataapi.hibernate.City;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public interface ICrudController<EntityIdType, Entity> {

    @GetMapping
    ResponseEntity<List<Entity>> getAllEntities();

    @GetMapping("/{id}")
    ResponseEntity<City> get(@PathVariable EntityIdType id);

    @PostMapping
    ResponseEntity<Entity> create(@RequestBody Entity entity);

    @PutMapping("/{id}")
    ResponseEntity<Entity> update(@PathVariable EntityIdType id, @RequestBody Entity entity);

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable EntityIdType id);
}
