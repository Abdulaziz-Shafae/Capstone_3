package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.LearningRequest;
import com.example.capstone_3.Model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningRequestRepository
        extends JpaRepository<LearningRequest, Integer> {

  LearningRequest findLearningRequestById(Integer id);

  List<LearningRequest> findByStatus(String status);

  List<LearningRequest> findAllBySkillAndStatus(Skill skill, String status);

  List<LearningRequest> findBySkill_IdAndStatus(
          Integer skillId, String status);
  @Query(value = "SELECT * FROM learning_request WHERE id = :id FOR UPDATE", nativeQuery = true)
  LearningRequest findLearningRequestForUpdate(@Param("id") Integer id);

  long countByRequesterAccount_Id(Integer accountId);

  long countByProviderAccount_Id(Integer accountId);

  List<LearningRequest> findByRequesterAccount_Id(
          Integer accountId);

  List<LearningRequest> findByProviderAccount_Id(
          Integer accountId);

  List<LearningRequest> findByUrgentTrueAndStatus(
          String status);
}
