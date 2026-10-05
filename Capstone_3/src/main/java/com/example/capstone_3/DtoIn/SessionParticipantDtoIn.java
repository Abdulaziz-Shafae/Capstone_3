package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionParticipantDtoIn {

    @NotBlank(message = "The participant status can't be blank")
    private String status;

    @NotNull(message = "The session ID can't be null")
    private Integer sessionId;

    @NotNull(message = "The exchange ID can't be null")
    private Integer exchangeId;
}

