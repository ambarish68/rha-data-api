package com.rha.dataapi.services;

import com.rha.dataapi.hibernate.Zone;
import com.rha.dataapi.repositories.ZoneRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ZoneService implements ICrudService<Zone, Integer> {

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Zone> getAll() {
        return zoneRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Zone get(Integer zoneId) {
        Optional<Zone> optionalZone = zoneRepository.findById(zoneId);
        if (optionalZone.isPresent()) {
            Zone existingZone = optionalZone.get();
            return existingZone;
        }
        throw new EntityNotFoundException("Zone with id: " + zoneId + " not found");
    }

    @Override
    public Zone create(Zone zoneToBeCreated) {
        if (zoneToBeCreated.getActive() == null) {
            zoneToBeCreated.setActive(true);
        }
        return zoneRepository.save(zoneToBeCreated);
    }

    @Override
    public Zone update(Integer zoneId, Zone zoneToBeUpdated) {
        Optional<Zone> optionalZone = zoneRepository.findById(zoneId);
        if (optionalZone.isPresent()) {
            Zone existingZone = optionalZone.get();
            existingZone.copyAttributes(zoneToBeUpdated);
            zoneRepository.save(existingZone);
            return existingZone;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Integer zoneId) {
        Optional<Zone> optionalZone = zoneRepository.findById(zoneId);
        if (optionalZone.isPresent()) {
            Zone existingZone = optionalZone.get();
            existingZone.setActive(false);
            zoneRepository.save(existingZone);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
