package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountById(Integer id);

}