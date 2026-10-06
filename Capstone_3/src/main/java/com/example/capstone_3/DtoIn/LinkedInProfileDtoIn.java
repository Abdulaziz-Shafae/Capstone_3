package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedInProfileDtoIn {

    @NotBlank(message = "LinkedIn profile URL is required")
    @Size(max = 500, message = "LinkedIn profile URL must not exceed 500 characters")
    private String profileUrl;
}