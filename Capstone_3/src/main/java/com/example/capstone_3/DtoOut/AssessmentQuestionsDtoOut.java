package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentQuestionsDtoOut {
 private Integer accountSkillId;
 private String skillName;
 private String currentLevel;
 private List<String>questions;











}
