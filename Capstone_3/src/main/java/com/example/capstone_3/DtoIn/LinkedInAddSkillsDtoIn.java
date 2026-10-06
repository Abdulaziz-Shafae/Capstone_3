package com.example.capstone_3.DtoIn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedInAddSkillsDtoIn {

    @NotEmpty(message = "Please select at least one skill")
    @Size(max = 100, message = "You can add at most 100 skills")
    private List<@NotBlank(message = "Skill name cannot be blank") @Size(max = 100, message = "Skill name must not exceed 100 characters") String> skills;
}