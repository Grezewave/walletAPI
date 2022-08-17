package com.wallet.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.wallet.dto.UserWalletDTO;
import com.wallet.wallet.entity.UserData;
import com.wallet.wallet.entity.UserWallet;
import com.wallet.wallet.entity.Wallet;
import com.wallet.wallet.response.Response;
import com.wallet.wallet.service.UserWalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-wallet")
public class UserWalletController {
    
    @Autowired
    private UserWalletService service;

    @PostMapping
    public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result){

        Response<UserWalletDTO> response = new Response<UserWalletDTO>();

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErros().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        UserWallet wallet = service.save(this.converDtoToEntity(dto));
        
        response.setData(this.converEntityToDto(wallet));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private UserWallet converDtoToEntity(UserWalletDTO dto){
        UserWallet uw = new UserWallet();
        uw.setId(dto.getId());

        UserData u = new UserData();
        u.setId(dto.getUsers());
        uw.setUsers(u);

        Wallet w = new Wallet();
        w.setId(dto.getWallet());
        uw.setWallet(w);

        return uw;
    }

    private UserWalletDTO converEntityToDto(UserWallet dto){
        UserWalletDTO u = new UserWalletDTO();

        u.setId(dto.getId());
        u.setUsers(dto.getUsers().getId());
        u.setWallet(dto.getWallet().getId());

        return u;
    }
}
