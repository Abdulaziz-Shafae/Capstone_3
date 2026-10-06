package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Account;
import com.example.capstone_3.Model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    Exchange findExchangeById(Integer id);
    @Query("select count(e) from Exchange e where e.skillOffer.providerAccount = ?1 and e.status = 'COMPLETED'")
    Integer countCompletedTeachings(Account provider);

}