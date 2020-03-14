package com.rha.dataapi.services;

import com.rha.dataapi.hibernate.Zone;
import com.rha.dataapi.repositories.ZoneRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Zone createZone(Zone zoneToBeCreated) {
        if (zoneToBeCreated.getActive() == null) {
            zoneToBeCreated.setActive(true);
        }
        return zoneRepository.save(zoneToBeCreated);
    }

    public Zone updateZone(Zone zoneToBeUpdated) {
        Optional<Zone> optionalZone = zoneRepository.findById(zoneToBeUpdated.getId());
        if (optionalZone.isPresent()) {
            Zone existingZone = optionalZone.get();
            existingZone.copyAttributes(zoneToBeUpdated);
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            existingZone.setModifiedBy(userDetails.getUsername());
            zoneRepository.save(existingZone);
            return existingZone;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteZone(Integer id) {
        Optional<Zone> optionalZone = zoneRepository.findById(id);
        if (optionalZone.isPresent()) {
            Zone existingZone = optionalZone.get();
            existingZone.setActive(false);
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            existingZone.setModifiedBy(userDetails.getUsername());
            zoneRepository.save(existingZone);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
