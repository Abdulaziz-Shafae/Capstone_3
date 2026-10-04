package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.LearningRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningRequestRepository extends JpaRepository<LearningRequest,Integer> {
  LearningRequest findLearningRequestById(Integer id);




}
