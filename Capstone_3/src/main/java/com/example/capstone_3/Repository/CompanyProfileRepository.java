package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Integer> {

    CompanyProfile findCompanyProfileById(Integer id);

}