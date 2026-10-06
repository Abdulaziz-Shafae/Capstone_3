package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementDtoIn {

    @NotBlank(message = "The agreement content can't be blank")
    private String content;

    @NotNull(message = "The exchange ID can't be null")
    private Integer exchangeId;

}

