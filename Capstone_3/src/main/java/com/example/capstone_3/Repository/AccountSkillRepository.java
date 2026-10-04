package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.AccountSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSkillRepository extends JpaRepository<AccountSkill,Integer> {
  AccountSkill findAllById(Integer id);



}
