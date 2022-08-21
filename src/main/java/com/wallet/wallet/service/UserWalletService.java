package com.wallet.wallet.service;

import org.springframework.stereotype.Service;

import com.wallet.wallet.entity.UserWallet;

@Service
public interface UserWalletService {
    
    UserWallet save(UserWallet uw);
}
