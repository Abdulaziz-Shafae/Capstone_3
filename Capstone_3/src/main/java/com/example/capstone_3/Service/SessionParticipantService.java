package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.SessionParticipantDtoIn;
import com.example.capstone_3.Model.Exchange;
import com.example.capstone_3.Model.Session;
import com.example.capstone_3.Model.SessionParticipant;
import com.example.capstone_3.Repository.ExchangeRepository;
import com.example.capstone_3.Repository.SessionParticipantRepository;
import com.example.capstone_3.Repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionParticipantService {

    private final SessionParticipantRepository sessionParticipantRepository;
    private final SessionRepository sessionRepository;
    private final ExchangeRepository exchangeRepository;

    public List<SessionParticipant> get() {
        return sessionParticipantRepository.findAll();
    }

    public void add(SessionParticipantDtoIn dto) {
        Session session = sessionRepository.findSessionById(dto.getSessionId());

        if (session == null) {
            throw new ApiException("No session found");
        }

        Exchange exchange = exchangeRepository.findExchangeById(dto.getExchangeId());

        if (exchange == null) {
            throw new ApiException("No exchange found");
        }

        SessionParticipant existingParticipant = sessionParticipantRepository.findSessionParticipantBySession_IdAndExchange_Id(dto.getSessionId(), dto.getExchangeId());

        if (existingParticipant != null) {
            throw new ApiException("This exchange is already participating in this session");
        }

        SessionParticipant participant = new SessionParticipant();
        participant.setStatus(dto.getStatus());
        participant.setSession(session);
        participant.setExchange(exchange);

        sessionParticipantRepository.save(participant);
    }

    public void update(Integer id, SessionParticipantDtoIn dto) {
        SessionParticipant oldParticipant = sessionParticipantRepository.findSessionParticipantById(id);

        if (oldParticipant == null) {
            throw new ApiException("No session participant found");
        }

        Session session = sessionRepository.findSessionById(dto.getSessionId());

        if (session == null) {
            throw new ApiException("No session found");
        }

        Exchange exchange = exchangeRepository.findExchangeById(dto.getExchangeId());

        if (exchange == null) {
            throw new ApiException("No exchange found");
        }

        SessionParticipant existingParticipant = sessionParticipantRepository.findSessionParticipantBySession_IdAndExchange_Id(dto.getSessionId(), dto.getExchangeId());

        if (existingParticipant != null && !existingParticipant.getId().equals(id)) {
            throw new ApiException("This exchange is already participating in this session");
        }

        oldParticipant.setStatus(dto.getStatus());
        oldParticipant.setSession(session);
        oldParticipant.setExchange(exchange);

        sessionParticipantRepository.save(oldParticipant);
    }

    public void delete(Integer id) {
        SessionParticipant participant = sessionParticipantRepository.findSessionParticipantById(id);

        if (participant == null) {
            throw new ApiException("No session participant found");
        }

        sessionParticipantRepository.delete(participant);
    }
}

