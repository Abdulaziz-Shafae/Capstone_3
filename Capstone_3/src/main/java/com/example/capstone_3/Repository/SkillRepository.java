package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Skill;
import com.example.capstone_3.Model.SkillOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Integer> {
    Skill findSkillById(Integer id);

    Skill findSkillByName(String name);


}
