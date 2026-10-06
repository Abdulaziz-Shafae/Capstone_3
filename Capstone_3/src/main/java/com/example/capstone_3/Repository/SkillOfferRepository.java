package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.Skill;
import com.example.capstone_3.Model.SkillOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillOfferRepository extends JpaRepository<SkillOffer,Integer> {
 SkillOffer findSkillOfferById(Integer id);
 List<SkillOffer>findAllBySkill(Skill skill);
 List<SkillOffer>findAllByProviderAccount(Account account);
 List<SkillOffer>findAllByStatus(String status);

 @Query("select s from SkillOffer s where s.providerAccount =?1 and s.skill=?2 and s.status=?3 ")
 List<SkillOffer>findActiveOffers(Account account,Skill skill,String status);

 @Query(value = "SELECT * FROM skill_offer WHERE id = :id FOR UPDATE", nativeQuery = true)
 SkillOffer findSkillOfferForUpdate(@Param("id") Integer id);

 long countByProviderAccount_Id(Integer accountId);

 long countByProviderAccount_IdAndStatus(Integer accountId, String status);

 Boolean existsByProviderAccountAndSkillAndModeAndTokenCostAndCapacityAndStatus(
         Account providerAccount, Skill skill, String mode, Integer tokenCost, Integer capacity, String status);


}
