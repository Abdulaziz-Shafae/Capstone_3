package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementGeneratorDtoOut {

    private Integer exchangeId;
    private Integer requesterId;
    private Integer providerId;
    private String skillName;
    private Integer tokenAmount;
    private String content;
    private Boolean aiGenerated;
}