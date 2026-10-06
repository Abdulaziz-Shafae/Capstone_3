package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDtoOut {

    private Integer accountId;
    private Integer tokenBalance;
    private Long reservedTokens;
    private Long requestsSent;
    private Long requestsReceived;
    private Long publishedOffers;
    private Long activeOffers;
    private Long totalExchanges;
    private Map<String, Long> exchangesByStatus;
    private Long tokensEarnedFromTeaching;
    private Long receivedReviews;
    private Double averageRating;
}