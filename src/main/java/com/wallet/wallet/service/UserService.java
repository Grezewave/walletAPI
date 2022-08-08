package com.wallet.wallet.service;

import java.util.Optional;

import com.wallet.wallet.entity.User;

public interface UserService {

    User save(User u);

    Optional<User> findByEmail(String email);

}
