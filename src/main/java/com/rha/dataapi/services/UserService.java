package com.rha.dataapi.services;

import com.rha.dataapi.hibernate.User;
import com.rha.dataapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements ICrudService<User, Integer> {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User get(Integer entityId) {
        return null;
    }

    @Override
    public User create(User entityToBeCreated) {
        return null;
    }

    @Override
    public User update(Integer entityId, User entityToBeUpdated) {
        return null;
    }

    @Override
    public void delete(Integer entityId) {

    }
}
