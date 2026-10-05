package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.IndividualProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualProfileRepository extends JpaRepository<IndividualProfile, Integer> {

    IndividualProfile findIndividualProfileById(Integer id);

    boolean existsByPhone(@NotBlank(message = "Phone number is required") @Pattern(regexp = "^05\\d{8}$", message = "Phone must be 10 digits and start with 05") String phone);
}