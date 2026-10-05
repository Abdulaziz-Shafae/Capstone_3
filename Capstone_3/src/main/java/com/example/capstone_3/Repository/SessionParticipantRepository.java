package com.example.capstone_3.Repository;

import com.example.capstone_3.Model.SessionParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionParticipantRepository
        extends JpaRepository<SessionParticipant, Integer> {

    SessionParticipant findSessionParticipantById(Integer id);

    SessionParticipant findSessionParticipantBySession_IdAndExchange_Id(
            Integer sessionId,
            Integer exchangeId
    );
}

