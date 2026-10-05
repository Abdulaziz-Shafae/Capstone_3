package com.example.capstone_3.DtoOut;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDtoOut {

    private Integer id;
    private Integer requestId;
    private Integer offerId;
    private Integer requesterAccountId;
    private String requesterName;
    private Integer providerAccountId;
    private String providerName;
    private Integer skillId;
    private String skillName;
    private String description;
    private String mode;
    private Integer baseTokens;
    private Integer urgentTokens;
    private Integer weekendTokens;
    private Integer tokenAmount;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime agreedDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime completedAt;
}