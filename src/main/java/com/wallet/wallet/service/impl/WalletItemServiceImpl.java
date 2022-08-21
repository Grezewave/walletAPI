package com.wallet.wallet.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wallet.wallet.entity.WalletItem;
import com.wallet.wallet.repository.WalletItemRepository;
import com.wallet.wallet.service.WalletItemService;
import com.wallet.wallet.util.enums.TypeEnum;

@Service
public class WalletItemServiceImpl implements WalletItemService {

    @Autowired
    WalletItemRepository repository;
    
    @Value("${pagination.items_per_page}")
    private int itemsPerPage;

    @Override
    public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page) {
        
        PageRequest pg = PageRequest.of(page, itemsPerPage);

        return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet, start, end, pg);
    }

    @Override
    public WalletItem save(WalletItem i) {
        return repository.save(i);
    }

    @Override
    public List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type) {
        // TODO Auto-generated method stub
        return repository.findByWalletIdAndType(wallet, type);
    }

    @Override
    public BigDecimal sumByWalletId(Long wallet) {
        // TODO Auto-generated method stub
        return repository.sumByWalletId(wallet);
    }

    @Override
    public Optional<WalletItem> findById(Long wallet) {
        // TODO Auto-generated method stub
        return repository.findById(wallet);
    }

    @Override
    public void deleteById(Long wallet) {
        repository.deleteById(wallet);
        
    }
    
}
