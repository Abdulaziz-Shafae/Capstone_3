package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.AccountSkill;
import com.example.capstone_3.Model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountSkillRepository extends JpaRepository<AccountSkill,Integer> {
  AccountSkill findAccountSkillById(Integer id);
  AccountSkill findAccountSkillByAccountAndSkill(Account account, Skill skill);
  List<AccountSkill>findAllByAccountAndVerified(Account account,Boolean verified);
  List<AccountSkill>findAllByAccount(Account account);
  List<AccountSkill>findAllBySkillAndVerified(Skill skill,Boolean verified);
}
