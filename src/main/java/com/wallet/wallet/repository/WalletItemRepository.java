package com.wallet.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.wallet.entity.WalletItem;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long>{
    
}
