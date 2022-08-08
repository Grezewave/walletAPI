package com.wallet.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.wallet.entity.User;
import com.wallet.wallet.repository.UserRepository;
import com.wallet.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository repository;

    @Override
    public User save(User u) {
        // TODO Auto-generated method stub
        return repository.save(u);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        return repository.findByEmailEquals(email);
    }

  
}
