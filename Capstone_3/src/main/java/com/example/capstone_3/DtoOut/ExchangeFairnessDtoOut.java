package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeFairnessDtoOut {

    private Integer requestId;
    private Integer offerId;
    private Integer exchangeId;
    private Long requestTotalTokens;
    private Integer offerTokens;
    private Long evaluatedTokens;
    private Boolean modeCompatible;
    private Integer fairnessScore;
    private String verdict;
    private String explanation;
    private List<String> concerns;
    private List<String> suggestions;
    private Boolean aiGenerated;
}