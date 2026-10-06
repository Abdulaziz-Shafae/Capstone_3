package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Session findSessionById(Integer id);

    List<Session> findBySkillOffer_Id(Integer offerId);

    List<Session> findDistinctBySessionParticipants_Exchange_Id(Integer exchangeId);
}
