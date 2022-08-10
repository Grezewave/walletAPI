package com.wallet.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.wallet.wallet.entity.UserData;

@Repository
@Component
public interface UserRepository extends JpaRepository<UserData, Long>{

    Optional<UserData> findByEmailEquals(String email);
}
