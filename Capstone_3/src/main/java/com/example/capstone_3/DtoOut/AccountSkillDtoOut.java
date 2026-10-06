package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSkillDtoOut {
    private Integer id;//Account Id
    private String skillName;//Skill
    private String category;//Skill
    private String level;//Account Skill
    private Boolean verified;//Account Skill
}
