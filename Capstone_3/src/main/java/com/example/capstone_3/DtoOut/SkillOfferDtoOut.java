package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillOfferDtoOut {
    private Integer id;
    private String skillName;
    private Integer providerAccountId;
    private String description;
    private String mode;
    private Integer tokenCost;
    private Integer capacity;
    private String status;

}
