package com.rha.dataapi.services;

import com.rha.dataapi.hibernate.Privilege;
import com.rha.dataapi.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrivilegeService implements ICrudService<Privilege, Integer> {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Privilege> getAll() {
        return privilegeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Privilege get(Integer entityId) {
        return null;
    }

    @Override
    public Privilege create(Privilege entityToBeCreated) {
        return null;
    }

    @Override
    public Privilege update(Integer entityId, Privilege entityToBeUpdated) {
        return null;
    }

    @Override
    public void delete(Integer entityId) {

    }
}
