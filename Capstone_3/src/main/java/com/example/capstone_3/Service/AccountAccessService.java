package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountAccessService {
    private final AccountRepository accountRepository;

    public Account requireActive(Integer accountId) {
        if (accountId == null) {
            throw new ApiException("Please log in first");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!"ACTIVE".equals(account.getStatus())) {
            throw new ApiException("Account is not active");
        }
        return account;
    }
}
