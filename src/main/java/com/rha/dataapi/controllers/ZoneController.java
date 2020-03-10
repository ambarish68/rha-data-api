package com.rha.dataapi.controllers;

import com.rha.dataapi.hibernate.Zone;
import com.rha.dataapi.services.ZoneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    private Logger logger = LogManager.getLogger(ZoneController.class);

    @GetMapping
    public List<Zone> getZones() {
        logger.info("getting all zones");
        return zoneService.getAllZones();
    }
}
