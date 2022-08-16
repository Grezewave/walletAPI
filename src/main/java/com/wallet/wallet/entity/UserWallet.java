package com.wallet.wallet.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users_wallet")
public class UserWallet implements Serializable{
    
    private static final long serialVersionUID = 548597653484L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserData users;
    @JoinColumn(name = "wallet", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;
}
