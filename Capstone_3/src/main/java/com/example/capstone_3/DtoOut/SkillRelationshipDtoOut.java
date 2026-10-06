package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillRelationshipDtoOut {
    private String skillName;
    private List<String>learnBefore;
    private List<String>learnWith;
    private List<String>learnAfter;
}
