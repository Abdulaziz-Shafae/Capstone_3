package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.AccountSkill;
import com.example.capstone_3.Model.SkillAssessment;
import com.example.capstone_3.Repository.AccountSkillRepository;
import com.example.capstone_3.Repository.SkillAssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillAssessmentService {
    private final SkillAssessmentRepository skillAssessmentRepository;
    private final AccountSkillRepository accountSkillRepository;


    public List<SkillAssessment>getAllSkillAssessments(){

        return skillAssessmentRepository.findAll();

    }


    public void addSkillAssessment(Integer accountSkillId,SkillAssessment skillAssessment){
        AccountSkill accountSkill=accountSkillRepository.findAccountSkillById(accountSkillId);
        if (accountSkill == null) {
            throw new ApiException("Account skill not found");
        }
        skillAssessment.setAccountSkill(accountSkill);
        skillAssessment.setAttemptedAt(LocalDateTime.now());
        skillAssessment.setAssessedLevel(calculateLevel(skillAssessment.getScore()));
        skillAssessmentRepository.save(skillAssessment);
        //طيب صار السكور اعلى من ٧٠ معناته ناجح على طول يتفعل حساب الاكونت سكل
        if(skillAssessment.getScore()>=70){
            accountSkill.setLevel(skillAssessment.getAssessedLevel());
            accountSkill.setVerified(true);
            accountSkillRepository.save(accountSkill);


        }
    }


    private String calculateLevel(Integer score){
        if(score>=90){
            return "EXPERT";
        }else if (score >= 80){
            return "ADVANCED";
        }else if (score >= 70) {
            return "INTERMEDIATE";
        }
        else {
            return "BEGINNER";
        }
    }

//ما ينفع احط ابديت هنا مو منطقي الا في حال وجود الادمن

//حتى الحذف الا لو فيه ادمن











































}
