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

import com.wallet.wallet.dto.UserDTO;
import com.wallet.wallet.entity.UserData;
import com.wallet.wallet.response.Response;
import com.wallet.wallet.service.UserService;
import com.wallet.wallet.util.Bcrypt;


@RestController
@RequestMapping("/user")
@Component
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result){

        Response<UserDTO> response = new Response<UserDTO>();

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErros().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        UserData user = service.save(this.converDtoToEntity(dto));
        
        response.setData(this.converEntityToDto(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 

    private UserData converDtoToEntity(UserDTO dto){
        UserData u = new UserData();
        u.setId(dto.getId());
        u.setEmail(dto.getEmail());
        u.setName(dto.getName());
        u.setPassword(Bcrypt.getHash(dto.getPassword()));

        return u;
    }

    private UserDTO converEntityToDto(UserData dto){
        UserDTO u = new UserDTO();
        u.setId(dto.getId());
        u.setEmail(dto.getEmail());
        u.setName(dto.getName());

        return u;
    }
    
}
