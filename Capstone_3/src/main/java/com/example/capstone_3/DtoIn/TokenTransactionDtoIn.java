package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenTransactionDtoIn {

    @NotNull(message = "The token amount can't be null")
    private Integer amount;

    @NotNull(message = "The transaction type can't be null")
    private String type;

    private String description;

    @NotNull(message = "The account ID can't be null")
    private Integer accountId;

    private Integer exchangeId;
}

