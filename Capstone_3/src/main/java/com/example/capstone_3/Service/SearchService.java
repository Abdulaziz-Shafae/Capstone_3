package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoOut.LearningRequestSearchDtoOut;
import com.example.capstone_3.DtoOut.ProviderDtoOut;
import com.example.capstone_3.Model.*;
import com.example.capstone_3.Repository.AccountSkillRepository;
import com.example.capstone_3.Repository.LearningRequestRepository;
import com.example.capstone_3.Repository.SkillOfferRepository;
import com.example.capstone_3.Repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SkillRepository skillRepository;
    private final AccountSkillRepository accountSkillRepository;
    private final SkillOfferRepository skillOfferRepository;
    private final LearningRequestRepository learningRequestRepository;




    public List<ProviderDtoOut>findProvidersBySkill(Integer skillId){
        Skill skill=skillRepository.findSkillById(skillId);
        if(skill==null){
            throw new ApiException("Skill not found");
        }
        List<AccountSkill>verifiedSkills=accountSkillRepository.findAllBySkillAndVerified(skill,true);
        List<ProviderDtoOut>result=new ArrayList<>();
        for(AccountSkill a:verifiedSkills){
            Account account = a.getAccount();
            List<SkillOffer> activeOffers = skillOfferRepository.findActiveOffers(account, skill, "ACTIVE");
          ProviderDtoOut dto=new ProviderDtoOut();
          dto.setAccountId(account.getId());
          dto.setProviderName(getAccountName(account));
          dto.setSkillName(a.getSkill().getName());
          dto.setLevel(a.getLevel());
          dto.setActiveOffers(activeOffers.size());
          result.add(dto);


        }
        return result;
    }

    public List<LearningRequestSearchDtoOut> findRequestsBySkill(Integer skillId) {
        Skill skill = skillRepository.findSkillById(skillId);
        if (skill == null) {
            throw new ApiException("Skill not found");
        }
        List<LearningRequest> requests = learningRequestRepository.findAllBySkillAndStatus(skill, "OPEN");
        List<LearningRequestSearchDtoOut> result = new ArrayList<>();

        for (LearningRequest r : requests) {
            Account requester = r.getRequesterAccount();
            Account provider = r.getProviderAccount();
            LearningRequestSearchDtoOut dto = new LearningRequestSearchDtoOut();
            if (provider != null) {
                dto.setProviderAccountId(provider.getId());
                dto.setProviderName(getAccountName(provider));
            }
            dto.setId(r.getId());
            dto.setRequesterAccountId(requester.getId());
            dto.setRequesterName(getAccountName(requester));
            dto.setSkillName(skill.getName());
            dto.setDescription(r.getDescription());
            dto.setMode(r.getMode());
            dto.setBaseTokens(r.getBaseTokens());
            dto.setWeekend(r.getWeekend());
            dto.setNeededBy(r.getNeededBy());
            dto.setStatus(r.getStatus());
            result.add(dto);
        }

        return result;
    }

    private String getAccountName(Account account) {
        if (account.getIndividualProfile() != null) {
            return account.getIndividualProfile().getName();
        }
        if (account.getCompanyProfile() != null) {
            return account.getCompanyProfile().getName();
        }
        return "Unknown";
    }
















}
