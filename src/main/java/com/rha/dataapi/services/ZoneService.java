package com.rha.dataapi.services;

import com.rha.dataapi.hibernate.Zone;
import com.rha.dataapi.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zone> getAllZones(){
        return zoneRepository.findAll();
    }
}
