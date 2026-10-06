package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.AccountSkill;
import com.example.capstone_3.Model.SkillAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillAssessmentRepository extends JpaRepository<SkillAssessment,Integer> {
 SkillAssessment findSkillAssessmentById(Integer id);
 List<SkillAssessment>findAllByAccountSkillOrderByAttemptedAtDesc(AccountSkill accountSkill);
 SkillAssessment findTopByAccountSkillOrderByAttemptedAtDesc(AccountSkill accountSkill);





}
