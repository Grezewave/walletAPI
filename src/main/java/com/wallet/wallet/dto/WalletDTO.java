package com.wallet.wallet.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import jakarta.validation.constraints.NotNull;


@Data
public class WalletDTO {
    
    public WalletDTO(){}

    private Long id;
    @Length(min = 3, message = "O nome deve conter mais que 3 caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    private String name;
    @NotNull(message = "O valor não pode ser nulo")
    private BigDecimal value;
}
