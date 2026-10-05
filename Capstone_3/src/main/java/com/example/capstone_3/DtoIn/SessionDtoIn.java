package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDtoIn {

    @NotBlank(message = "The session title can't be blank")
    private String title;

    @NotNull(message = "The scheduled date can't be null")
    private LocalDateTime scheduledAt;

    @NotNull(message = "The duration can't be null")
    @Min(value = 1, message = "The duration must be at least one minute")
    private Integer durationMinutes;

    @NotBlank(message = "The session mode can't be blank")
    @Pattern(regexp = "ONLINE|IN_PERSON", message = "The session mode must be ONLINE or IN_PERSON")
    private String mode;

    private String meetingLink;

    private String location;

    @NotBlank(message = "The session status can't be blank")
    @Pattern(regexp = "SCHEDULED|COMPLETED|CANCELLED", message = "The session status must be SCHEDULED, COMPLETED, or CANCELLED")
    private String status;

    @NotNull(message = "The skill offer ID can't be null")
    private Integer skillOfferId;
}


