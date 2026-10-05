package com.example.capstone_3.DtoIn;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestNegotiationDtoIn {
    @NotBlank(message = "Message is required")
    @Size(max = 500, message = "Message must be at most 500 characters")
    private String message;

    @Future(message = "Proposed date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime proposedDate;
}
