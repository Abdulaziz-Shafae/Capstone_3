package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.TokenTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenTransactionRepository
        extends JpaRepository<TokenTransaction, Integer> {

    TokenTransaction findTokenTransactionById(Integer id);

    List<TokenTransaction> findTokenTransactionsByAccount_Id(Integer accountId);

    List<TokenTransaction> findTokenTransactionsByExchange_Id(Integer exchangeId);
    List<TokenTransaction>findAllByAccountOrderByCreatedAtDesc(Account account);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TokenTransaction t WHERE t.account.id = :accountId AND t.type = 'TEACHING' AND t.amount > 0")
    Long sumTeachingTokens(@Param("accountId") Integer accountId);

}

