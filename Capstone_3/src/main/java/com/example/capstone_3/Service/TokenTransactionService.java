package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.TokenTransactionDtoIn;
import com.example.capstone_3.DtoOut.TokenTransactionDtoOut;
import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.Exchange;
import com.example.capstone_3.Model.TokenTransaction;
import com.example.capstone_3.Repository.AccountRepository;
import com.example.capstone_3.Repository.ExchangeRepository;
import com.example.capstone_3.Repository.TokenTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // ================= {Token endpoints}=================

    // 1 token = 10 SAR
    private static final int tokenPrice = 10;
    private static final int teachingForBonus = 5;
    private static final int BonusAmount = 5;

    //43 done
    public Integer getBalance(Integer accountId){
        Account account=getAccount(accountId);
        return account.getTokenBalance();
    }

    //44 done
    public List<TokenTransactionDtoOut>getHistory(Integer accountId){
        Account account=getAccount(accountId);
        List<TokenTransaction> transactions = tokenTransactionRepository.findAllByAccountOrderByCreatedAtDesc(account);
        List<TokenTransactionDtoOut> result = new ArrayList<>();
        for (TokenTransaction t : transactions) {
            TokenTransactionDtoOut dto = new TokenTransactionDtoOut();
            dto.setId(t.getId());
            dto.setAmount(t.getAmount());
            dto.setType(t.getType());
            dto.setDescription(t.getDescription());
            if (t.getExchange() != null) {
                dto.setExchangeId(t.getExchange().getId());
            }
            dto.setCreatedAt(t.getCreatedAt());
            result.add(dto);
        }

        return result;

    }

    //45
    // #45 Give bonus tokens (every 5 completed teachings = 5 bonus tokens)
    @Transactional
    public void giveBonus(Integer accountId) {
        Account account = getActiveAccount(accountId);

        //completed exchanges where he was the teacher
        int teachings = exchangeRepository.countCompletedTeachings(account);
        // bonuses he already received
        int bonuses = 0;
        for (TokenTransaction t : tokenTransactionRepository.findTokenTransactionsByAccount_Id(accountId)) {
            if (t.getType().equals("BONUS")) {
                bonuses++;
            }
        }
        int deserved=teachings/teachingForBonus;
        if (bonuses>=deserved) {
            int remaining=teachingForBonus-(teachings % teachingForBonus);
            throw new ApiException("Not eligible for a bonus yet. Teach " + remaining + " more times");
        }
        account.setTokenBalance(account.getTokenBalance()+BonusAmount);
        accountRepository.save(account);
        saveTransaction(account, null, BonusAmount, "BONUS",
                "Bonus for completing " + (deserved * teachingForBonus) + " teachings");
    }
    // #46
    @Transactional
    public void refundExchange(Integer exchangeId) {
        Exchange exchange=exchangeRepository.findExchangeById(exchangeId);
        if (exchange==null) {
            throw new ApiException("Exchange not found");
        }
        if (!exchange.getStatus().equals("CANCELLED")) {
            throw new ApiException("Only cancelled exchanges can be refunded");
        }
        //  make sure it was not refunded before
        TokenTransaction payment=null;
        List<TokenTransaction>exchangeTransactions = tokenTransactionRepository.findTokenTransactionsByExchange_Id(exchangeId);
        for (TokenTransaction t:exchangeTransactions) {
            if (t.getType().equals("REFUND")) {
                throw new ApiException("This exchange is already refunded");
            }
            if (t.getType().equals("TEACHING")) {
                throw new ApiException("Teacher already received tokens, cannot refund");
            }
            if (t.getType().equals("LEARNING")) {
                payment=t;
            }
        }
        if (payment==null) {
            throw new ApiException("No payment found for this exchange");
        }
        Account learner = payment.getAccount();
        int refundAmount = Math.abs(payment.getAmount());
        learner.setTokenBalance(learner.getTokenBalance()+refundAmount);
        accountRepository.save(learner);
        saveTransaction(learner, exchange, refundAmount, "REFUND", "Refund for cancelled exchange");
    }

    // 47 Purchase tokens using money
    @Transactional
    public Integer purchaseTokens(Integer accountId, Integer amount) {
        if (amount==null||amount<=0) {
            throw new ApiException("Amount must be positive");
        }
        if (amount > 1000) {
            throw new ApiException("You can purchase at most 1000 tokens at once");
        }
        Account account = getActiveAccount(accountId);
        account.setTokenBalance(account.getTokenBalance() + amount);
        accountRepository.save(account);
        int price=amount*tokenPrice;
        saveTransaction(account, null, amount, "PURCHASE", "Purchased " +amount+" tokens for "+ price + " SAR");
        return price;
    }

    //48
    @Transactional
    public Integer redeemTokens(Integer accountId, Integer amount) {
        if (amount==null||amount<=0) {
            throw new ApiException("Amount must be positive");
        }
        if (amount<5) {
            throw new ApiException("Minimum redeem is 5 tokens");
        }
        Account account = getActiveAccount(accountId);
        if (account.getTokenBalance()<amount) {
            throw new ApiException("Not enough tokens");
        }
        account.setTokenBalance(account.getTokenBalance()-amount);
        accountRepository.save(account);
        int money=amount*tokenPrice;
        saveTransaction(account, null, -amount, "REDEMPTION", "Redeemed " + amount + " tokens for " + money + " SAR");
        return money;
    }

    //nvm methods

    private Account getAccount(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        return account;
    }

    //sure it is active
    private Account getActiveAccount(Integer accountId) {
        Account account = getAccount(accountId);
        if (!account.getStatus().equals("ACTIVE")) {
            throw new ApiException("Account is not active");
        }
        return account;
    }
    private void saveTransaction(Account account, Exchange exchange, Integer amount, String type, String description) {
        TokenTransaction transaction = new TokenTransaction();
        transaction.setAccount(account);
        transaction.setExchange(exchange);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setCreatedAt(LocalDateTime.now());
        tokenTransactionRepository.save(transaction);
    }

















}

