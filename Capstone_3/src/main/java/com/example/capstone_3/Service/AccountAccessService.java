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
        return checkActive(requireExisting(accountId));
    }

    public void requireLogin(Integer accountId) {
        if (accountId == null) {
            throw new ApiException("Please log in first");
        }
    }

    public Account requireExisting(Integer accountId) {
        requireLogin(accountId);
        Account account = accountRepository.findAccountById(accountId);
        return checkExisting(account);
    }

    public Account checkExisting(Account account) {
        if (account == null) {
            throw new ApiException("Account not found");
        }
        return account;
    }

    public Account checkActive(Account account) {
        checkExisting(account);
        if (!"ACTIVE".equals(account.getStatus())) {
            throw new ApiException("Account is not active");
        }
        return account;
    }

    public Account requireForTokenUpdate(Integer accountId) {
        requireLogin(accountId);
        Account account = checkActive(accountRepository.findAccountForTokenUpdate(accountId));
        if (account.getTokenBalance() == null) {
            throw new ApiException("Account token balance is invalid");
        }
        return account;
    }

    public Account requireForVerification(Integer accountId) {
        requireLogin(accountId);
        return checkActive(accountRepository.findAccountForVerification(accountId));
    }
}
