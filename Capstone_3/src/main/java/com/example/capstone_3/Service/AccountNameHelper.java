package com.example.capstone_3.Service;

import com.example.capstone_3.Model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountNameHelper {

    public String getAccountName(Account account) {

        if (account == null) {
            return "Unknown";
        }

        if ("INDIVIDUAL".equals(account.getAccountType()) && account.getIndividualProfile() != null) {
            return account.getIndividualProfile().getName();
        }

        if ("COMPANY".equals(account.getAccountType()) && account.getCompanyProfile() != null) {
            return account.getCompanyProfile().getName();
        }

        return "Unknown";
    }
}