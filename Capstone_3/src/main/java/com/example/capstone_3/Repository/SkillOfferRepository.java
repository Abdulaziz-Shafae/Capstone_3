package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.SkillOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillOfferRepository extends JpaRepository<SkillOffer,Integer> {
 SkillOffer findSkillOfferById(Integer id);






}
