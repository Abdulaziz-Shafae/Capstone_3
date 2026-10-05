package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.TokenTransactionDtoIn;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.Exchange;
import com.example.capstone_3.Model.TokenTransaction;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.ExchangeRepository;
import com.example.capstone_3.Repository.TokenTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenTransactionService {

    private final TokenTransactionRepository tokenTransactionRepository;
    private final AccountRepository accountRepository;
    private final ExchangeRepository exchangeRepository;

    public List<TokenTransaction> get() {
        return tokenTransactionRepository.findAll();
    }

    public List<TokenTransaction> getByAccountId(Integer accountId) {
        return tokenTransactionRepository.findTokenTransactionsByAccount_Id(accountId);
    }

    public List<TokenTransaction> getByExchangeId(Integer exchangeId) {
        return tokenTransactionRepository.findTokenTransactionsByExchange_Id(exchangeId);
    }

    public void add(TokenTransactionDtoIn dto) {
        Account account = accountRepository.findAccountById(dto.getAccountId());

        if (account == null) {
            throw new ApiException("No account found");
        }

        Exchange exchange = null;

        if (dto.getExchangeId() != null) {
            exchange = exchangeRepository.findExchangeById(dto.getExchangeId());

            if (exchange == null) {
                throw new ApiException("No exchange found");
            }
        }

        TokenTransaction transaction = new TokenTransaction();
        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setDescription(dto.getDescription());
        transaction.setAccount(account);
        transaction.setExchange(exchange);

        tokenTransactionRepository.save(transaction);
    }

    public void update(Integer id, TokenTransactionDtoIn dto) {

        TokenTransaction oldTransaction = tokenTransactionRepository.findTokenTransactionById(id);

        if (oldTransaction == null) {
            throw new ApiException("No token transaction found");
        }

        Account account = accountRepository.findAccountById(dto.getAccountId());

        if (account == null) {
            throw new ApiException("No account found");
        }

        Exchange exchange = null;

        if (dto.getExchangeId() != null) {
            exchange = exchangeRepository.findExchangeById(dto.getExchangeId());

            if (exchange == null) {
                throw new ApiException("No exchange found");
            }
        }

        oldTransaction.setAmount(dto.getAmount());
        oldTransaction.setType(dto.getType());
        oldTransaction.setDescription(dto.getDescription());
        oldTransaction.setAccount(account);
        oldTransaction.setExchange(exchange);

        tokenTransactionRepository.save(oldTransaction);
    }

    public void delete(Integer id) {
        TokenTransaction transaction = tokenTransactionRepository.findTokenTransactionById(id);

        if (transaction == null) {
            throw new ApiException("No token transaction found");
        }

        tokenTransactionRepository.delete(transaction);
    }
}

