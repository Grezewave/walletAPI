package com.wallet.wallet.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.wallet.entity.WalletItem;
import com.wallet.wallet.util.enums.TypeEnum;

@Repository
public interface WalletItemRepository extends JpaRepository<WalletItem, Long>{
    
    Page<WalletItem> findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(Long wallet, Date init, Date end, Pageable page);

    List<WalletItem> findByWalletIdAndType(Long wallet, TypeEnum type);
}
