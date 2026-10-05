package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.LearningRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningRequestRepository
        extends JpaRepository<LearningRequest, Integer> {

  LearningRequest findLearningRequestById(Integer id);

  List<LearningRequest> findByStatus(String status);

  List<LearningRequest> findBySkill_IdAndStatus(
          Integer skillId, String status);

  List<LearningRequest> findByRequesterAccount_Id(
          Integer accountId);

  List<LearningRequest> findByProviderAccount_Id(
          Integer accountId);

  List<LearningRequest> findByUrgentTrueAndStatus(
          String status);
}
