package com.wallet.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wallet.wallet.entity.WalletItem;
import com.wallet.wallet.util.enums.TypeEnum;

@Service
@Component
public interface WalletItemService {
    
    WalletItem save(WalletItem i);
    
    Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);

    List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type);

    BigDecimal sumByWalletId(Long wallet);

    Optional<WalletItem> findById(Long wallet);

    void deleteById(Long wallet);
}
