package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.SkillOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillOfferRepository extends JpaRepository<SkillOffer,Integer> {
 SkillOffer findSkillOfferById(Integer id);


 @Query(value = "SELECT * FROM skill_offer WHERE id = :id FOR UPDATE", nativeQuery = true)
 SkillOffer findSkillOfferForUpdate(@Param("id") Integer id);

 long countByProviderAccount_Id(Integer accountId);

 long countByProviderAccount_IdAndStatus(Integer accountId, String status);

}
