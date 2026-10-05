package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoOut.SkillOfferDtoOut;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.AccountSkill;
import com.example.capstone_3.Model.Skill;
import com.example.capstone_3.Model.SkillOffer;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.AccountSkillRepository;
import com.example.capstone_3.Repository.SkillOfferRepository;
import com.example.capstone_3.Repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class SkillOfferService {
    private final SkillOfferRepository skillOfferRepository;
    private final AccountRepository accountRepository;
    private final SkillRepository skillRepository;
    private final AccountSkillRepository accountSkillRepository;


    public List<SkillOffer>getSkillOffer(){
        return skillOfferRepository.findAll();
    }

    public void addOffer(Integer accountId,Integer skillId,SkillOffer skillOffer){
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        Skill skill = skillRepository.findSkillById(skillId);
        if (skill == null) {
            throw new ApiException("Skill not found");
        }
       //تبي تسوي offer خلي عندك مهاره وخلك موثق
        AccountSkill accountSkill=accountSkillRepository.findAccountSkillByAccountAndSkill(account,skill);
        if (accountSkill == null) {
            throw new ApiException("You don't have this skill");
        }
        if (!accountSkill.getVerified()) {
            throw new ApiException("You must pass the skill assessment before offering it");
        }
        skillOffer.setProviderAccount(account);
        skillOffer.setSkill(skill);
        skillOffer.setStatus("ACTIVE");
        skillOffer.setCreatedAt(LocalDateTime.now());
        skillOfferRepository.save(skillOffer);
    }



    public void updateSkillOffer(Integer id,SkillOffer skillOffer){
        SkillOffer oldOffer = skillOfferRepository.findSkillOfferById(id);
        if (oldOffer == null) {
            throw new ApiException("Skill offer not found");
        }
        if(oldOffer.getStatus().equals("CANCELLED")){
            throw new ApiException("Cancelled offer cannot be updated");
        }
        oldOffer.setDescription(skillOffer.getDescription());
        oldOffer.setMode(skillOffer.getMode());
        oldOffer.setTokenCost(skillOffer.getTokenCost());
        oldOffer.setCapacity(skillOffer.getCapacity());
        skillOfferRepository.save(oldOffer);

    }


    public void deleteSkillOffer(Integer id) {
        SkillOffer skillOffer = skillOfferRepository.findSkillOfferById(id);
        if (skillOffer == null) {
            throw new ApiException("Skill offer not found");
        }
        // لو فيه exchange عليه، ما نحذفه عشان ما تنحذف معه
        if (skillOffer.getExchanges()!=null&&!skillOffer.getExchanges().isEmpty()) {
            throw new ApiException("Offer has exchanges and cannot be deleted");
        }

        skillOfferRepository.delete(skillOffer);
    }


//endPOINT 27 DONE
   public List<SkillOfferDtoOut>getOffersBySkill(Integer skillId){
      Skill skill=skillRepository.findSkillById(skillId);
      if(skill==null){
          throw new ApiException("Skill not found");
      }
      List<SkillOffer>skillOffers=skillOfferRepository.findAllBySkill(skill);
      List<SkillOfferDtoOut>result=new ArrayList<>();
      for(SkillOffer s:skillOffers){
          SkillOfferDtoOut dto=new SkillOfferDtoOut();
          dto.setId(s.getId());
          dto.setCapacity(s.getCapacity());
          dto.setMode(s.getMode());
          dto.setStatus(s.getStatus());
          dto.setDescription(s.getDescription());
          dto.setProviderAccountId(s.getProviderAccount().getId());
          dto.setTokenCost(s.getTokenCost());
          dto.setSkillName(s.getSkill().getName());
          result.add(dto);
      }
      return result;
    }

   //ENDPOINT 28 done
   public List<SkillOfferDtoOut>getOffersCreatedByProvider(Integer providerId){
    Account account=accountRepository.findAccountById(providerId);
    if(account==null){
        throw new ApiException("Account not found");
    }

    List<SkillOffer>skillOffers=skillOfferRepository.findAllByProviderAccount(account);
    List<SkillOfferDtoOut>result=new ArrayList<>();
    for(SkillOffer s:skillOffers){
        SkillOfferDtoOut dto=new SkillOfferDtoOut();
        dto.setId(s.getId());
        dto.setSkillName(s.getSkill().getName());
        dto.setProviderAccountId(s.getProviderAccount().getId());
        dto.setDescription(s.getDescription());
        dto.setMode(s.getMode());
        dto.setTokenCost(s.getTokenCost());
        dto.setCapacity(s.getCapacity());
        dto.setStatus(s.getStatus());
        result.add(dto);
    }
    return result;

   }

    //ENDPOINT 29 done
   public List<SkillOfferDtoOut>getActiveOffers(){
        List<SkillOffer>skillOffers=skillOfferRepository.findAllByStatus("ACTIVE");
        List<SkillOfferDtoOut>result=new ArrayList<>();
        for(SkillOffer s:skillOffers){
            SkillOfferDtoOut dto=new SkillOfferDtoOut();
            dto.setId(s.getId());
            dto.setSkillName(s.getSkill().getName());
            dto.setProviderAccountId(s.getProviderAccount().getId());
            dto.setDescription(s.getDescription());
            dto.setMode(s.getMode());
            dto.setTokenCost(s.getTokenCost());
            dto.setCapacity(s.getCapacity());
            dto.setStatus(s.getStatus());
            result.add(dto);
        }
        return result;
   }















}
