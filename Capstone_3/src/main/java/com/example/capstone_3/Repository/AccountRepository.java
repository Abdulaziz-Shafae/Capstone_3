package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountById(Integer id);

    Account findAccountByEmail(@NotBlank(message = "The email cant be blank") @NotEmpty(message = "The email cant be empty") @Email(message = "Email must be valid") @Size(max = 150, message = "Email must not exceed 150 characters") String email);
}