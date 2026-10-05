package com.example.capstone_3.DtoOut;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestNegotiationDtoOut {

    private Integer id;
    private Integer senderAccountId;
    private String senderName;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime proposedDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    private Integer urgentTokens;
    private Integer weekendTokens;
}