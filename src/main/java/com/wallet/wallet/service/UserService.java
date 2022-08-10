package com.wallet.wallet.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wallet.wallet.entity.UserData;

@Service
@Component
public interface UserService {

    UserData save(UserData u);

    Optional<UserData> findByEmail(String email);

}
