package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.AccountDtoIn;
import com.example.capstone_3.DtoIn.LoginDtoIn;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> get(){
        return accountRepository.findAll();
    }


    public void add(AccountDtoIn accountDtoIn){

        Account oldAccount = accountRepository.findAccountByEmail(accountDtoIn.getEmail());

        if(oldAccount != null){
            throw new ApiException("Email already exists");
        }

        Account account = new Account();

        account.setEmail(accountDtoIn.getEmail());
        account.setPassword(accountDtoIn.getPassword());
        account.setAccountType(accountDtoIn.getAccountType());

        account.setTokenBalance(3);
        account.setStatus("ACTIVE");
        account.setEmailVerified(false);
        account.setCreatedAt(LocalDateTime.now());

        accountRepository.save(account);
    }


    public void update(Integer id, AccountDtoIn accountDtoIn){

        Account oldAccount = accountRepository.findAccountById(id);

        if(oldAccount == null){
            throw new ApiException("No account found");
        }

        Account emailAccount = accountRepository.findAccountByEmail(accountDtoIn.getEmail());

        if(emailAccount != null && !emailAccount.getId().equals(id)){
            throw new ApiException("Email already exists");
        }

        oldAccount.setEmail(accountDtoIn.getEmail());
        oldAccount.setPassword(accountDtoIn.getPassword());
        oldAccount.setAccountType(accountDtoIn.getAccountType());

        accountRepository.save(oldAccount);
    }


    public void delete(Integer id){

        Account oldAccount = accountRepository.findAccountById(id);

        if(oldAccount == null){
            throw new ApiException("No account found");
        }

        accountRepository.delete(oldAccount);
    }


    public Integer login(LoginDtoIn loginDtoIn){

        Account oldAcc = accountRepository.findAccountByEmail(loginDtoIn.getEmail());

        if(oldAcc == null){
            throw new ApiException("Email not found");
        }

        oldAcc = accountRepository.findAccountByEmailAndPassword(
                loginDtoIn.getEmail(), loginDtoIn.getPassword());

        if(oldAcc == null){
            throw new ApiException("Wrong password");
        }

        return oldAcc.getId();
    }



}