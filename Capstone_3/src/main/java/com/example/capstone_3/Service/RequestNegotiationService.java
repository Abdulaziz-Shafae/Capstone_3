package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.RequestNegotiationDtoIn;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.LearningRequest;
import com.example.capstone_3.Model.RequestNegotiation;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.LearningRequestRepository;
import com.example.capstone_3.Repository.RequestNegotiationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestNegotiationService {

    private final RequestNegotiationRepository requestNegotiationRepository;
    private final LearningRequestRepository learningRequestRepository;
    private final AccountRepository accountRepository;


    public List<RequestNegotiation> get(){
        return requestNegotiationRepository.findAll();
    }


    public void add(Integer requestId, Integer senderAccountId, RequestNegotiationDtoIn requestNegotiationDtoIn){

        LearningRequest learningRequest = learningRequestRepository.findLearningRequestById(requestId);

        if(learningRequest == null){
            throw new ApiException("No learning request found");
        }

        Account senderAccount = accountRepository.findAccountById(senderAccountId);

        if(senderAccount == null){
            throw new ApiException("No account found");
        }

        RequestNegotiation requestNegotiation = new RequestNegotiation();

        requestNegotiation.setMessage(requestNegotiationDtoIn.getMessage());
        requestNegotiation.setProposedDate(requestNegotiationDtoIn.getProposedDate());

        requestNegotiation.setCreatedAt(LocalDateTime.now());
        requestNegotiation.setLearningRequest(learningRequest);
        requestNegotiation.setSenderAccount(senderAccount);

        requestNegotiationRepository.save(requestNegotiation);
    }


    public void update(Integer id, RequestNegotiationDtoIn requestNegotiationDtoIn){

        RequestNegotiation oldRequestNegotiation = requestNegotiationRepository.findRequestNegotiationById(id);

        if(oldRequestNegotiation == null){
            throw new ApiException("No request negotiation found");
        }

        oldRequestNegotiation.setMessage(requestNegotiationDtoIn.getMessage());
        oldRequestNegotiation.setProposedDate(requestNegotiationDtoIn.getProposedDate());

        requestNegotiationRepository.save(oldRequestNegotiation);
    }


    public void delete(Integer id){

        RequestNegotiation oldRequestNegotiation = requestNegotiationRepository.findRequestNegotiationById(id);

        if(oldRequestNegotiation == null){
            throw new ApiException("No request negotiation found");
        }

        requestNegotiationRepository.delete(oldRequestNegotiation);
    }

}