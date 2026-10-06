package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIAssessmentDtoIn {
    @NotEmpty(message = "Questions are required")
    private String questions;

    @NotEmpty(message = "Answers are required")
    private String answers;

}
