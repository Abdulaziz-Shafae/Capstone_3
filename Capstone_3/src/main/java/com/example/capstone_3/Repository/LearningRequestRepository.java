package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.LearningRequest;
import com.example.capstone_3.Model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningRequestRepository extends JpaRepository<LearningRequest,Integer> {
  LearningRequest findLearningRequestById(Integer id);
  List<LearningRequest>findAllBySkillAndStatus(Skill skill,String status);



}
