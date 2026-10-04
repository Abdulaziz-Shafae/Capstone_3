package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.IndividualProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualProfileRepository extends JpaRepository<IndividualProfile, Integer> {

    IndividualProfile findIndividualProfileById(Integer id);

}