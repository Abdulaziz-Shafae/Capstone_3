package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoOut.AccountSkillDtoOut;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.AccountSkill;
import com.example.capstone_3.Model.Skill;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.AccountSkillRepository;
import com.example.capstone_3.Repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountSkillService {
    private final AccountAccessService accountAccessService;
    private final AccountSkillRepository accountSkillRepository;
    private final AccountRepository accountRepository;
    private final SkillRepository skillRepository;


    public List<AccountSkill>getAccountSkills() {
        return accountSkillRepository.findAll();
    }




    public void addAccountSkill(Integer accountId,Integer skillId){
        Account account=accountAccessService.requireActive(accountId);
        Skill skill=skillRepository.findSkillById(skillId);
        if (skill == null) {
            throw new ApiException("Skill not found");
        }
        if(accountSkillRepository.findAccountSkillByAccountAndSkill(account,skill)!=null){
            throw new ApiException("Account already has this skill");
        }
        AccountSkill accountSkill=new AccountSkill();
       accountSkill.setAccount(account);
       accountSkill.setSkill(skill);
       accountSkill.setLevel("BEGINNER");
        accountSkill.setVerified(false);
        accountSkillRepository.save(accountSkill);
    }

    //  ل اي احد يقدر يحط نفسه اكسبرت بدون ما يختبر الابديت ذا للادمن او نشيله
    public void updateAccountSkill(Integer id,AccountSkill accountSkill){
        AccountSkill oldAccountSkill =accountSkillRepository.findAccountSkillById(id);
        if(oldAccountSkill ==null){
            throw new ApiException("Account skill not found");
        }
        oldAccountSkill.setLevel(accountSkill.getLevel());
        oldAccountSkill.setVerified(accountSkill.getVerified());
        accountSkillRepository.save(oldAccountSkill);
    }

    public void deleteAccountSkill(Integer id) {
        AccountSkill accountSkill = accountSkillRepository.findAccountSkillById(id);
        if (accountSkill == null) {
            throw new ApiException("Account skill not found");
        }
        accountSkillRepository.delete(accountSkill);
    }


    //endpoint 8 done
    public List<AccountSkillDtoOut>getSkillsByAccount(Integer accountId){
      Account account=accountRepository.findAccountById(accountId);
      if(account==null){
          throw new ApiException("Account not found");
      }

      List<AccountSkill>accountSkills=accountSkillRepository.findAllByAccount(account);
      List<AccountSkillDtoOut>result=new ArrayList<>();

      for(AccountSkill a: accountSkills ){
          AccountSkillDtoOut dto = new AccountSkillDtoOut();
          dto.setId(a.getId());
          dto.setSkillName(a.getSkill().getName());
          dto.setCategory(a.getSkill().getCategory());
          dto.setLevel(a.getLevel());
          dto.setVerified(a.getVerified());

          result.add(dto);
      }
      return result;


    }

    //endPoint 9 done
    public List<AccountSkillDtoOut>getVerifiedSkillsOfAccount(Integer accountId){
     Account account=accountRepository.findAccountById(accountId);
     if(account==null){
         throw new ApiException("Account not found");
     }

     List<AccountSkill>accountSkills=accountSkillRepository.findAllByAccountAndVerified(account,true);
        List<AccountSkillDtoOut>result=new ArrayList<>();
        for(AccountSkill a:accountSkills){
            AccountSkillDtoOut dto=new AccountSkillDtoOut();
            dto.setId(a.getId());
            dto.setLevel(a.getLevel());
            dto.setVerified(a.getVerified());
            dto.setSkillName(a.getSkill().getName());
            dto.setCategory(a.getSkill().getCategory());
            result.add(dto);
        }

        return result;
    }


}
