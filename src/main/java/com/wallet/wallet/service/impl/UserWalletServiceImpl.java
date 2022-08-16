package com.wallet.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.wallet.entity.UserWallet;
import com.wallet.wallet.repository.UserWalletRepository;
import com.wallet.wallet.service.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService {

    @Autowired
    private UserWalletRepository repository;
    
    @Override
    public UserWallet save(UserWallet uw) {
        // TODO Auto-generated method stub
        return repository.save(uw);
    }
    
}
