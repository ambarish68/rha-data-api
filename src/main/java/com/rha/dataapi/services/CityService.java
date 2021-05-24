package com.rha.dataapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.rha.dataapi.Constants;
import com.rha.dataapi.hibernate.City;
import com.rha.dataapi.hibernate.Status;
import com.rha.dataapi.repositories.CityRepository;
import com.rha.dataapi.repositories.StatusRepository;
import com.rha.dataapi.search.GenericSpecification;
import com.rha.dataapi.filters.SearchCriteria;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class CityService implements ICrudService<City, Integer> {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StatusRepository statusRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional(readOnly = true)
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public City get(Integer cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            return optionalCity.get();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<City> getWithPredicate(List<SearchCriteria> searchCriteriaList) {
        GenericSpecification genericSpecification = GenericSpecification.builder().list(searchCriteriaList).build();
        return cityRepository.findAll(genericSpecification);
    }

    @Override
    public City create(City cityToBeCreated) {
        Preconditions.checkNotNull(cityToBeCreated, "Cannot create a null city");
        Preconditions.checkNotNull(cityToBeCreated.getZone(), "Cannot create a city without a zone");
        try {
            if (!Objects.nonNull(cityToBeCreated.getActive())) {
                cityToBeCreated.setActive(true);
            }
            if (!Objects.nonNull(cityToBeCreated.getStatus())) {
                Optional<Status> newStatus = statusRepository.findStatusByName(Constants.NEW_CITY);
                if (newStatus.isPresent()) {
                    cityToBeCreated.setStatus(newStatus.get());
                }
            }
            log.info("creating city with the parameters: " + objectMapper.writeValueAsString(cityToBeCreated));
            cityToBeCreated = cityRepository.save(cityToBeCreated);
            return cityToBeCreated;
        } catch (Exception e) {
            log.error("Exception creating city with parameters: " + cityToBeCreated, e);
            throw new IllegalArgumentException("City not created with given parameters ", e);
        }
    }

    @Override
    public City update(Integer cityId, City cityToBeUpdated) {
        Preconditions.checkNotNull(cityId, "City id cannot be null");
        Preconditions.checkNotNull(cityToBeUpdated, "Update parameters cannot be null");
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            City existingCity = optionalCity.get();
            existingCity.copyAttributes(cityToBeUpdated);
            return cityRepository.save(existingCity);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void delete(Integer cityId) {
        Preconditions.checkNotNull(cityId, "City id cannot be null");
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            City existingCity = optionalCity.get();
            existingCity.setActive(false);
            cityRepository.save(existingCity);
            return;
        }
        throw new EntityNotFoundException();
    }
}
