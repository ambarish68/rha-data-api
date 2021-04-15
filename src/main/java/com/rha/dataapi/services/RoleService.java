package com.rha.dataapi.services;

import com.rha.dataapi.hibernate.Role;
import com.rha.dataapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService  implements ICrudService<Role, Integer> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role get(Integer entityId) {
        return null;
    }

    @Override
    public Role create(Role entityToBeCreated) {
        return null;
    }

    @Override
    public Role update(Integer entityId, Role entityToBeUpdated) {
        return null;
    }

    @Override
    public void delete(Integer entityId) {

    }
}
