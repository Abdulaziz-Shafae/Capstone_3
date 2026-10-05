package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.SessionDtoIn;
import com.example.capstone_3.Model.Session;
import com.example.capstone_3.Model.SkillOffer;
import com.example.capstone_3.Repository.SessionRepository;
import com.example.capstone_3.Repository.SkillOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SkillOfferRepository skillOfferRepository;

    public List<Session> get() {
        return sessionRepository.findAll();
    }

    public void add(SessionDtoIn sessionDtoIn) {
        SkillOffer skillOffer = skillOfferRepository.findSkillOfferById(sessionDtoIn.getSkillOfferId());

        if (skillOffer == null) {
            throw new ApiException("No skill offer found");
        }

        Session session = new Session();

        session.setTitle(sessionDtoIn.getTitle());
        session.setScheduledAt(sessionDtoIn.getScheduledAt());
        session.setDurationMinutes(sessionDtoIn.getDurationMinutes());
        session.setMode(sessionDtoIn.getMode());
        session.setMeetingLink(sessionDtoIn.getMeetingLink());
        session.setLocation(sessionDtoIn.getLocation());
        session.setStatus(sessionDtoIn.getStatus() != null ? sessionDtoIn.getStatus() : "SCHEDULED");
        session.setSkillOffer(skillOffer);

        sessionRepository.save(session);
    }

    public void update(Integer id, SessionDtoIn sessionDtoIn) {
        Session oldSession = sessionRepository.findSessionById(id);

        if (oldSession == null) {
            throw new ApiException("No session found");
        }

        SkillOffer skillOffer = skillOfferRepository.findSkillOfferById(sessionDtoIn.getSkillOfferId());

        if (skillOffer == null) {
            throw new ApiException("No skill offer found");
        }

        oldSession.setTitle(sessionDtoIn.getTitle());
        oldSession.setScheduledAt(sessionDtoIn.getScheduledAt());
        oldSession.setDurationMinutes(sessionDtoIn.getDurationMinutes());
        oldSession.setMode(sessionDtoIn.getMode());
        oldSession.setMeetingLink(sessionDtoIn.getMeetingLink());
        oldSession.setLocation(sessionDtoIn.getLocation());
        oldSession.setStatus(sessionDtoIn.getStatus());
        oldSession.setSkillOffer(skillOffer);

        sessionRepository.save(oldSession);
    }

    public void delete(Integer id) {
        Session oldSession = sessionRepository.findSessionById(id);

        if (oldSession == null) {
            throw new ApiException("No session found");
        }

        sessionRepository.delete(oldSession);
    }
}

