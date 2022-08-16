package com.wallet.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.wallet.entity.Wallet;
import com.wallet.wallet.repository.WalletRepository;
import com.wallet.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService{
    
    @Autowired
    private WalletRepository repository;

    @Override
    public Wallet save(Wallet w){
        return repository.save(w);
    }
}
