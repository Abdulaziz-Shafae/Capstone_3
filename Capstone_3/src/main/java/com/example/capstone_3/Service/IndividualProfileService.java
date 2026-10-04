package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.IndividualProfileDtoIn;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.IndividualProfile;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.IndividualProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualProfileService {

    private final IndividualProfileRepository individualProfileRepository;
    private final AccountRepository accountRepository;


    public List<IndividualProfile> get(){
        return individualProfileRepository.findAll();
    }


    public void add(IndividualProfileDtoIn individualProfileDtoIn){

        Account account = accountRepository.findAccountById(individualProfileDtoIn.getAccountId());

        if(account == null){
            throw new ApiException("No account found");
        }

        IndividualProfile individualProfile = new IndividualProfile();

        individualProfile.setName(individualProfileDtoIn.getName());
        individualProfile.setPhone(individualProfileDtoIn.getPhone());
        individualProfile.setBio(individualProfileDtoIn.getBio());
        individualProfile.setCity(individualProfileDtoIn.getCity());
        individualProfile.setProfileImage(individualProfileDtoIn.getProfileImage());
        individualProfile.setAccount(account);

        individualProfileRepository.save(individualProfile);
    }


    public void update(Integer id, IndividualProfileDtoIn individualProfileDtoIn){

        IndividualProfile oldIndividualProfile = individualProfileRepository.findIndividualProfileById(id);

        if(oldIndividualProfile == null){
            throw new ApiException("No individual profile found");
        }

        Account account = accountRepository.findAccountById(individualProfileDtoIn.getAccountId());

        if(account == null){
            throw new ApiException("No account found");
        }

        oldIndividualProfile.setName(individualProfileDtoIn.getName());
        oldIndividualProfile.setPhone(individualProfileDtoIn.getPhone());
        oldIndividualProfile.setBio(individualProfileDtoIn.getBio());
        oldIndividualProfile.setCity(individualProfileDtoIn.getCity());
        oldIndividualProfile.setProfileImage(individualProfileDtoIn.getProfileImage());
        oldIndividualProfile.setAccount(account);

        individualProfileRepository.save(oldIndividualProfile);
    }


    public void delete(Integer id){

        IndividualProfile oldIndividualProfile = individualProfileRepository.findIndividualProfileById(id);

        if(oldIndividualProfile == null){
            throw new ApiException("No individual profile found");
        }

        individualProfileRepository.delete(oldIndividualProfile);
    }

}