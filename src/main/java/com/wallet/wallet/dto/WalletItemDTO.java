package com.wallet.wallet.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.wallet.wallet.entity.Wallet;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class WalletItemDTO {

    private Long id;
    @NotNull(message = "Insira o ID da carteira")
    private Long wallet;
    @NotNull(message = "Informe uma data")
    private String date;
    @NotNull(message = "Informe um tipo")
    @Pattern(regexp = "^(ENTRADA|SAIDA)$", message = "O tipo deve ser ENTRADA ou SAIDA")
    private String type;
    @NotNull(message = "Informe uma descricao")
    @Length(min = 5,message = "A descricao deve ter no minimo 5 caracteres")
    private String description;
    @NotNull
    private BigDecimal value;

}