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
public class TokenTransactionDtoOut {

    private Integer id;
    private Integer amount;
    private String type;
    private String description;
    private Integer exchangeId;
    private LocalDateTime createdAt;
}
