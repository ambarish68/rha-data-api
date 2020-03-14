package com.rha.dataapi.services;

import com.rha.dataapi.hibernate.Status;
import com.rha.dataapi.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}
