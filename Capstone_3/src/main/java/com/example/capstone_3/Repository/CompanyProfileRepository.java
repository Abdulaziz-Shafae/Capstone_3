package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.CompanyProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Integer> {

    CompanyProfile findCompanyProfileById(Integer id);

    boolean existsByPhone(@NotBlank(message = "Phone number is required") @Pattern(regexp = "^05[0-9]{8}$", message = "Phone must be 10 digits and start with 05") String phone);
}