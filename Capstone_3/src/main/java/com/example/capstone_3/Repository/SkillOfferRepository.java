package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.Skill;
import com.example.capstone_3.Model.SkillOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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



}
