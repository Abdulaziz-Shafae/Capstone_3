package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.LearningRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningRequestRepository extends JpaRepository<LearningRequest,Integer> {
  LearningRequest findLearningRequestById(Integer id);

  @Query(value = "SELECT * FROM learning_request WHERE id = :id FOR UPDATE", nativeQuery = true)
  LearningRequest findLearningRequestForUpdate(@Param("id") Integer id);

  long countByRequesterAccount_Id(Integer accountId);

  long countByProviderAccount_Id(Integer accountId);

}
