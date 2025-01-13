package com.dbmsproject.fellowtraveller.services;

import com.dbmsproject.fellowtraveller.models.User;

import com.dbmsproject.fellowtraveller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByID(Long id) {
        return userRepository.findById(id);
    }

//    public void InsertUser(User user) {
//        userRepository.InsertUser(user);
//    }

    public User saveUser(User user) throws SQLIntegrityConstraintViolationException {
        return userRepository.save(user);
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    public void deleteByName(String name) {
        userRepository.deleteByName(name);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}