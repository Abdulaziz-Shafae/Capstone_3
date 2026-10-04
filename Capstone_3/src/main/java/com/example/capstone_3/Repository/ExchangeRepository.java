package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    Exchange findExchangeById(Integer id);

}