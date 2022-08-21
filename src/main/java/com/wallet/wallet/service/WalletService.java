package com.wallet.wallet.service;

import org.springframework.stereotype.Service;

import com.wallet.wallet.entity.Wallet;

@Service
public interface WalletService {
    
    Wallet save(Wallet w);
}
