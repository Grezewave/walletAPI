package com.wallet.wallet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserWalletDTO {
    
    private Long id;
    @NotNull(message = "Informe o ID do usuario")
    private Long users;
    @NotNull(message = "Informe o ID da carteira")
    private Long wallet;
}
