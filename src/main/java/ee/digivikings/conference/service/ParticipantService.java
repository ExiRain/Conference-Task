package ee.digivikings.conference.service;

import ee.digivikings.conference.service.dto.ParticipantDTO;

import java.util.List;

public interface ParticipantService {
    /**
     * Get all participants.
     *
     * @return list with all participants.
     */
    List<ParticipantDTO> getAllParticipants();

    /**
     * Get Participant by specified ID.
     *
     * @param id of participant to find.
     * @return participant the by specified id.
     */
    ParticipantDTO getById(Long id);

    /**
     * Create new Participant.
     *
     * @param participant - Participant to be created.
     * @return created Participant.
     */
    ParticipantDTO createParticipant(ParticipantDTO participant);

    /**
     * Remove specified participant from the conference.
     *
     * @param participantId specified participant to remove from Conference.
     * @return
     */
    Integer removeParticipantFromConference(Long participantId);

    /**
     * Add specific participant to specified Conference.
     *
     * @param conferenceId  - Conference to add participant to.
     * @param participantId - Participant to add to specified Conference.
     * @return
     */
    Integer addParticipantToConference(Long conferenceId, Long participantId);

    /**
     * Delete specific participant.
     *
     * @param id of the Participant to be deleted.
     */
    void deleteParticipantById(Long id);
}
