package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class IndividualProfileDtoIn {

    @NotNull(message = "The name cant be null")
    @NotEmpty(message = "The name cant be empty")
    @NotBlank(message = "The name cant be blank")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;


    @NotNull(message = "The phone cant be null")
    @NotEmpty(message = "The phone cant be empty")
    @NotBlank(message = "The phone cant be blank")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone must be 10 digits and start with 05")
    private String phone;


    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;


    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;


    @Size(max = 500, message = "Profile image must not exceed 500 characters")
    private String profileImage;


    @NotNull(message = "The account id cant be null")
    private Integer accountId;
}