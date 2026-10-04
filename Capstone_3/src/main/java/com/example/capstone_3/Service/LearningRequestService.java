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

    public void addLearningRequest(Integer accountId, Integer skillId, LearningRequest learningRequest) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        Skill skill = skillRepository.findSkillById(skillId);
        if (skill == null) {
            throw new ApiException("Skill not found");
        }
        calculateExtraTokens(learningRequest);
        if (account.getTokenBalance()<totalTokens(learningRequest)) {
            throw new ApiException("Not enough tokens");
        }
        learningRequest.setRequesterAccount(account);
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
        oldRequest.setUrgent(learningRequest.getUrgent());
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






    private Integer totalTokens(LearningRequest learningRequest) {
        return learningRequest.getBaseTokens()+learningRequest.getUrgentTokens()+learningRequest.getWeekendTokens();
    }
    private void calculateExtraTokens(LearningRequest learningRequest) {
        if (learningRequest.getUrgent()!=null&&learningRequest.getUrgent()) {
            learningRequest.setUrgentTokens(5);
        } else {
            learningRequest.setUrgentTokens(0);
        }if (learningRequest.getWeekend()!=null&&learningRequest.getWeekend()) {
            learningRequest.setWeekendTokens(2);
        } else {
            learningRequest.setWeekendTokens(0);
        }
    }
}