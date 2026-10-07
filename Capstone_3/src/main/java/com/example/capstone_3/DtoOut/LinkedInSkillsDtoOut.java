package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedInSkillsDtoOut {

    private Integer accountId;
    private String profileUrl;
    private List<String> newSkills;
    private List<String> rareSkills;
    private List<String> availableSkills;
    private List<String> ownedSkills;
}