package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.CompanyProfileDtoIn;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.CompanyProfile;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.CompanyProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyProfileService {

    private final CompanyProfileRepository companyProfileRepository;
    private final AccountRepository accountRepository;


    public List<CompanyProfile> get(){
        return companyProfileRepository.findAll();
    }


    public void add(CompanyProfileDtoIn companyProfileDtoIn){

        Account account = accountRepository.findAccountById(companyProfileDtoIn.getAccountId());

        if(account == null){
            throw new ApiException("No account found");
        }

        CompanyProfile companyProfile = new CompanyProfile();

        companyProfile.setName(companyProfileDtoIn.getName());
        companyProfile.setDescription(companyProfileDtoIn.getDescription());
        companyProfile.setPhone(companyProfileDtoIn.getPhone());
        companyProfile.setCity(companyProfileDtoIn.getCity());
        companyProfile.setLogo(companyProfileDtoIn.getLogo());
        companyProfile.setVerified(false);
        companyProfile.setAccount(account);

        companyProfileRepository.save(companyProfile);
    }


    public void update(Integer id, CompanyProfileDtoIn companyProfileDtoIn){

        CompanyProfile oldCompanyProfile = companyProfileRepository.findCompanyProfileById(id);

        if(oldCompanyProfile == null){
            throw new ApiException("No company profile found");
        }

        Account account = accountRepository.findAccountById(companyProfileDtoIn.getAccountId());

        if(account == null){
            throw new ApiException("No account found");
        }

        oldCompanyProfile.setName(companyProfileDtoIn.getName());
        oldCompanyProfile.setDescription(companyProfileDtoIn.getDescription());
        oldCompanyProfile.setPhone(companyProfileDtoIn.getPhone());
        oldCompanyProfile.setCity(companyProfileDtoIn.getCity());
        oldCompanyProfile.setLogo(companyProfileDtoIn.getLogo());
        oldCompanyProfile.setAccount(account);

        companyProfileRepository.save(oldCompanyProfile);
    }


    public void delete(Integer id){

        CompanyProfile oldCompanyProfile = companyProfileRepository.findCompanyProfileById(id);

        if(oldCompanyProfile == null){
            throw new ApiException("No company profile found");
        }

        companyProfileRepository.delete(oldCompanyProfile);
    }

}