package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.Skill;
import com.example.capstone_3.Repository.SkillRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    public List<Skill>getAllSkills(){
        return skillRepository.findAll();
    }

    public void addSkill(Skill skill){

        //الاسم يونيك
        Skill skillName=skillRepository.findSkillByName(skill.getName());
        if(skillName!=null){
            throw new ApiException("Skill already exists");
        }
        skillRepository.save(skill);
    }

    public void updateSkill(Integer id,Skill skill){
       Skill oldSkill= skillRepository.findSkillById(id);
       if(oldSkill==null){
           throw new ApiException("Skill not found");
       }
       Skill name=skillRepository.findSkillByName(skill.getName());
       if(name!=null && !name.getId().equals(id)){
           throw new ApiException("Skill name already used");
       }
       oldSkill.setCategory(skill.getCategory());
       oldSkill.setName(skill.getName());
       oldSkill.setDescription(skill.getDescription());
       skillRepository.save(oldSkill);

    }


    public void deleteSkill(Integer id){
        Skill skill=skillRepository.findSkillById(id);
        if(skill==null){
            throw new ApiException("Skill not found");
        }
//        if(!skill.getAccountSkills().isEmpty()||!skill.getLearningRequests().isEmpty()||!skill.getSkillOffers().isEmpty()){
//            throw new ApiException("Skill is in use and cannot be deleted");
//        } // اختياريه
        skillRepository.delete(skill);
    }








}
