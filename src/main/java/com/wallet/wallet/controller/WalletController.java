package com.wallet.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.wallet.dto.WalletDTO;
import com.wallet.wallet.entity.Wallet;
import com.wallet.wallet.response.Response;
import com.wallet.wallet.service.WalletService;


@RestController
@RequestMapping("/wallet")
@Component
public class WalletController {
    
    @Autowired
    private WalletService service;

    @PostMapping
    public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO dto, BindingResult result){

        Response<WalletDTO> response = new Response<WalletDTO>();

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErros().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Wallet wallet = service.save(this.converDtoToEntity(dto));
        
        response.setData(this.converEntityToDto(wallet));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Wallet converDtoToEntity(WalletDTO dto){
        Wallet u = new Wallet();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setValue(dto.getValue());

        return u;
    }

    private WalletDTO converEntityToDto(Wallet dto){
        WalletDTO u = new WalletDTO();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setValue(dto.getValue());

        return u;
    }
}
