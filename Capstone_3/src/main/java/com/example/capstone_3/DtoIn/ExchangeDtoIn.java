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
public class ExchangeDtoIn {

    @NotNull(message = "The token amount cant be null")
    private Integer tokenAmount;

}