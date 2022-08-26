package ee.digivikings.conference.service;

import ee.digivikings.conference.domain.Conference;
import ee.digivikings.conference.service.dto.ConferenceDTO;

import java.util.List;

/**
 * Service Interface for managing {@link Conference}.
 *
 * @author Vassili Mosklajov.
 * @version 1.0
 */
public interface  ConferenceService {
    /**
     * Get all conferences.
     *
     * @return the list of all conferences.
     */
    List<ConferenceDTO> getAllConferences();

    /**
     * Get Conference by specified ID.
     *
     * @param id of conference to find.
     * @return conference the by specified id.
     */
    ConferenceDTO getById(Long id);

    /**
     * Create new conference.
     *
     * @param conference - Conference to be created.
     * @return created Conference.
     */
    ConferenceDTO createConference(ConferenceDTO conference);

    /**
     * Cancel the conference.
     *
     * @param conferenceId id of the Conference to cancel.
     * @return
     */
    Integer cancelConference(Long conferenceId);

    /**
     * Delete the conference.
     *
     * @param conferenceId id of the Conference to delete.
     */
    void deleteConference(Long conferenceId);

    /**
     * Get free seats for specified Conference.
     *
     * @param id of the Conference to get free seats for.
     * @return created Conference.
     */
    Integer getFreeSlotsForConference(Long id);
}
