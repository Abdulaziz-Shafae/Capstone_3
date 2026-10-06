package com.example.capstone_3.DtoOut;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LearningRequestDtoOut {

    private Integer id;
    private Integer requesterAccountId;
    private Integer providerAccountId;
    private Integer skillId;
    private String skillName;
    private Integer offerId;
    private String description;
    private String mode;
    private Integer baseTokens;
    private Boolean urgent;
    private Integer urgentTokens;
    private Boolean weekend;
    private Integer weekendTokens;
    private Integer totalTokens;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime neededBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    private List<RequestNegotiationDtoOut> negotiationHistory;
}