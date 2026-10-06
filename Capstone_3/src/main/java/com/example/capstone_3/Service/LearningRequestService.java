package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.LearningRequest;
import com.example.capstone_3.Model.Skill;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.LearningRequestRepository;
import com.example.capstone_3.Repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningRequestService {
    private final LearningRequestRepository learningRequestRepository;
    private final AccountRepository accountRepository;
    private final SkillRepository skillRepository;

    public List<LearningRequest> getAllLearningRequests() {
        return learningRequestRepository.findAll();
    }

    public void addLearningRequest(Integer accountId, Integer skillId, Integer providerAccountId, LearningRequest learningRequest){

        Account account = accountRepository.findAccountById(accountId);

        if(account == null){
            throw new ApiException("Account not found");
        }

        Skill skill = skillRepository.findSkillById(skillId);

        if(skill == null){
            throw new ApiException("Skill not found");
        }

        Account providerAccount = accountRepository.findAccountById(providerAccountId);

        if(providerAccount == null){
            throw new ApiException("Provider account not found");
        }

        if(accountId.equals(providerAccountId)){
            throw new ApiException("Requester and provider must be different");
        }

        calculateExtraTokens(learningRequest);
        learningRequest.setUrgent(false);
        learningRequest.setUrgentTokens(0);

        if (account.getTokenBalance()<totalTokens(learningRequest)) {
            throw new ApiException("Not enough tokens");
        }

        learningRequest.setId(null);
        learningRequest.setExchange(null);
        learningRequest.setRequestNegotiations(null);

        learningRequest.setRequesterAccount(account);
        learningRequest.setProviderAccount(providerAccount);
        learningRequest.setSkill(skill);
        learningRequest.setStatus("OPEN");
        learningRequest.setCreatedAt(LocalDateTime.now());

        learningRequestRepository.save(learningRequest);
    }


    public void updateLearningRequest(Integer id, LearningRequest learningRequest){

        LearningRequest oldRequest = learningRequestRepository.findLearningRequestById(id);
        if (oldRequest == null) {
            throw new ApiException("Learning request not found");
        }
        if (!oldRequest.getStatus().equals("OPEN")) {
            throw new ApiException("Only open requests can be updated");
        }
        oldRequest.setDescription(learningRequest.getDescription());
        oldRequest.setMode(learningRequest.getMode());
        oldRequest.setWeekend(learningRequest.getWeekend());
        oldRequest.setBaseTokens(learningRequest.getBaseTokens());
        oldRequest.setNeededBy(learningRequest.getNeededBy());
        calculateExtraTokens(oldRequest);
        if (oldRequest.getRequesterAccount().getTokenBalance() < totalTokens(oldRequest)) {
            throw new ApiException("Not enough tokens");
        }
        learningRequestRepository.save(oldRequest);

    }

    public void deleteLearningRequest(Integer id) {
        LearningRequest learningRequest = learningRequestRepository.findLearningRequestById(id);
        if (learningRequest == null) {
            throw new ApiException("Learning request not found");
        }if (!learningRequest.getStatus().equals("OPEN")) {
            throw new ApiException("Only open requests can be deleted");
        }
        learningRequestRepository.delete(learningRequest);
    }


    public List<LearningRequest> getOpenLearningRequests() {
        return learningRequestRepository.findByStatus("OPEN");
    }

    public List<LearningRequest> getLearningRequestsBySkill(Integer skillId) {
        if (skillRepository.findSkillById(skillId) == null) {
            throw new ApiException("Skill not found");
        }

        return learningRequestRepository.findBySkill_IdAndStatus(skillId, "OPEN");
    }

    public List<LearningRequest> getRequestsByRequester(Integer accountId) {
        if (accountRepository.findAccountById(accountId) == null) {
            throw new ApiException("Account not found");
        }

        return learningRequestRepository.findByRequesterAccount_Id(accountId);
    }

    public List<LearningRequest> getRequestsByProvider(Integer accountId) {
        if (accountRepository.findAccountById(accountId) == null) {
            throw new ApiException("Account not found");
        }

        return learningRequestRepository.findByProviderAccount_Id(accountId);
    }

    public List<LearningRequest> getUrgentLearningRequests() {
        return learningRequestRepository.findByUrgentTrueAndStatus("OPEN");
    }

    public void cancelLearningRequest(Integer requestId) {
        LearningRequest request = learningRequestRepository.findLearningRequestById(requestId);

        if (request == null) {
            throw new ApiException("Learning request not found");
        }

        if (!"OPEN".equals(request.getStatus())) {
            throw new ApiException("Only open requests can be cancelled");
        }

        request.setStatus("CANCELLED");
        learningRequestRepository.save(request);
    }


// نرجع لها
    private Integer totalTokens(LearningRequest learningRequest) {
        return learningRequest.getBaseTokens()+learningRequest.getUrgentTokens()+learningRequest.getWeekendTokens();
    }
    private void calculateExtraTokens(LearningRequest learningRequest) {
        if (learningRequest.getWeekend()!=null&&learningRequest.getWeekend()) {
            learningRequest.setWeekendTokens(2);
        } else {
            learningRequest.setWeekendTokens(0);
        }
    }
}