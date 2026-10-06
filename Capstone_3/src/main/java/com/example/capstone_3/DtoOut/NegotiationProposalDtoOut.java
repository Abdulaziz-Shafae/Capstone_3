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
public class NegotiationProposalDtoOut {

    private Integer requestId;
    private Integer negotiationId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime proposedDate;

    private Integer baseTokens;
    private Integer urgentTokens;
    private Integer weekendTokens;
    private Integer totalTokens;
    private Integer tokenBalance;
    private Boolean enoughTokens;
}