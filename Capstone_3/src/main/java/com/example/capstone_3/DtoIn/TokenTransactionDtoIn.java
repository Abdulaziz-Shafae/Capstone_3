package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Min(value = 1, message = "The token amount must be at least 1")
    private Integer amount;

    @NotNull(message = "The transaction type can't be null")
    @Pattern(regexp = "STARTING|TEACHING|LEARNING|BONUS|REFUND|PURCHASE|REDEMPTION", message = "The transaction type must be STARTING, TEACHING, LEARNING, BONUS, REFUND, PURCHASE or REDEMPTION")
    private String type;

    private String description;

    @NotNull(message = "The account ID can't be null")
    private Integer accountId;

    private Integer exchangeId;
}


