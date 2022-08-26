package ee.digivikings.conference.service.impl;

import ee.digivikings.conference.domain.Participant;
import ee.digivikings.conference.repository.ParticipantRepository;
import ee.digivikings.conference.service.ParticipantService;
import ee.digivikings.conference.service.dto.ParticipantDTO;
import ee.digivikings.conference.service.mapper.ParticipantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Service Implementation for managing {@link Participant}.
 *
 * @author Vassili Moskaljov.
 * @version 1.0
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantMapper participantMapper;
    private final ParticipantRepository participantRepository;

    @Override
    public List<ParticipantDTO> getAllParticipants() {
        log.debug("Fetching all participants");
        return participantMapper.toDto(participantRepository.findAll());
    }

    @Override
    public ParticipantDTO getById(Long id) {
        log.debug("Fetching participant by id: {}", id);
        Optional<Participant> participant = participantRepository.findById(id);
        return participant.map(participantMapper::toDto).orElse(null);
    }

    @Override
    public ParticipantDTO createParticipant(ParticipantDTO participant) {
        log.debug("Creating new participant {}", participant);
        Participant convertedParticipant = participantMapper.toEntity(participant);
        if (participant.getConferenceId() == null) {
            convertedParticipant.setConference(null);
        }
        Participant savedParticipant = participantRepository.save(convertedParticipant);
        return participantMapper.toDto(savedParticipant);
    }

    @Transactional
    @Override
    public Integer removeParticipantFromConference(Long participantId) {
        log.debug("Removing conference from participant {}", participantId);
        return participantRepository.updateConferenceId(null, participantId);
    }

    @Transactional
    @Override
    public Integer addParticipantToConference(Long conferenceId, Long participantId) {
        log.debug("Adding conference {} to participant {}", conferenceId, participantId);
        return participantRepository.updateConferenceId(conferenceId, participantId);
    }

    @Override
    public void deleteParticipantById(Long id) {
        log.debug("Deleting participant {}", id);
        participantRepository.deleteById(id);
    }
}

