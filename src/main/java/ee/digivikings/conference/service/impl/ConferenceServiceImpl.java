package ee.digivikings.conference.service.impl;

import ee.digivikings.conference.domain.Conference;
import ee.digivikings.conference.domain.enumeration.ConferenceStatus;
import ee.digivikings.conference.repository.ConferenceRepository;
import ee.digivikings.conference.service.ConferenceService;
import ee.digivikings.conference.service.dto.ConferenceDTO;
import ee.digivikings.conference.service.mapper.ConferenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Conference}.
 *
 * @author Vassili Moskaljov.
 * @version 1.0
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;
    private final ConferenceMapper conferenceMapper;

    @Override
    public List<ConferenceDTO> getAllConferences() {
        log.debug("Fetching all conference records.");
        List<Conference> result = conferenceRepository.findAll();
        return conferenceMapper.toDto(result);
    }

    @Override
    public ConferenceDTO getById(Long id) {
        log.debug("Fetching conference by id: {}", id);
        Optional<Conference> conference = conferenceRepository.findById(id);
        return conference.map(conferenceMapper::toDto).orElse(null);
    }

    @Override
    public ConferenceDTO createConference(ConferenceDTO conference) {
        log.debug("Creating new conference: {}", conference);
        Conference convertedConference = conferenceMapper.toEntity(conference);
        convertedConference.setStatus(ConferenceStatus.ACTIVE);
        Conference savedConference = conferenceRepository.save(convertedConference);
        return conferenceMapper.toDto(savedConference);
    }

    @Override
    @Transactional
    public Integer cancelConference(Long conferenceId) {
        log.debug("Canceling the conference: {}", conferenceId);
        return conferenceRepository.updateConferenceStatus(conferenceId, ConferenceStatus.CANCELED);
    }

    @Override
    public void deleteConference(Long conferenceId) {
        log.debug("Deleting conference: {}", conferenceId);
        Optional<Conference> byId = conferenceRepository.findById(conferenceId);
        byId.ifPresent(conferenceRepository::delete);
    }

    @Override
    public Integer getFreeSlotsForConference(Long id) {
        log.debug("Fetching available slots for conference: {}", id);
        Optional<Conference> byId = conferenceRepository.findById(id);
        return byId.map(this::getAvailableSeats).orElse(-1);
    }

    protected int getAvailableSeats(Conference conference) {
        int maxSeatCount = conference.getRoom().getMaxSeats();
        int totalParticipants = conference.getParticipants().size();
        return maxSeatCount - totalParticipants;
    }
}
