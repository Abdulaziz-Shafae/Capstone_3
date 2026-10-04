package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDtoIn {

    @NotBlank(message = "The email cant be blank")
    @NotEmpty(message = "The email cant be empty")
    @Email(message = "Email must be valid")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;


    @NotBlank(message = "The password cant be blank")
    @NotEmpty(message = "The password cant be empty")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;


    @NotBlank(message = "The account type cant be blank")
    @NotEmpty(message = "The account type cant be empty")
    @Pattern(
            regexp = "^(INDIVIDUAL|COMPANY|ADMIN)$",
            message = "Account type must be INDIVIDUAL, COMPANY, or ADMIN"
    )
    private String accountType;
}