package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LearningRequestSearchDtoOut {

    private Integer id;
    private Integer providerAccountId;
    private String providerName;
    private Integer requesterAccountId;
    private String requesterName;
    private String skillName;
    private String description;
    private String mode;
    private Integer baseTokens;
    private Boolean weekend;
    private LocalDateTime neededBy;
    private String status;


}
