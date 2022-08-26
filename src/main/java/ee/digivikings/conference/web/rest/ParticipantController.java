package ee.digivikings.conference.web.rest;

import ee.digivikings.conference.domain.Participant;
import ee.digivikings.conference.service.ParticipantService;
import ee.digivikings.conference.service.dto.ParticipantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
@RequestMapping("/api/v1/participant")
public class ParticipantController {
    private final ParticipantService participantService;

    /**
     * {@code GET } : Method to retrieve List of all participants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body List<{@link ee.digivikings.conference.domain.Participant}>.
     */
    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> getAllParticipants() {
        log.info("REST request to get all participants");
        return ResponseEntity.ok(participantService.getAllParticipants());
    }

    /**
     * {@code GET /:id} : Method to retrieve Participant by id.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body {@link Participant}
     * or {@code 204 (NO_CONTENT)} if result is null.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable Long id) {
        log.info("REST request to get participant by id : {}", id);
        ParticipantDTO result = participantService.getById(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * {@code POST  /create} : Creates new Participant.
     *
     * @param participant the Participant to create.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the created Participant.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ParticipantDTO> createParticipant(@RequestBody @Valid ParticipantDTO participant) {
        log.info("REST request to create new participant: {}", participant);
        ParticipantDTO result = participantService.createParticipant(participant);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code PUT  /conference-add/:conferenceId/:participantId} : Add Participant to the conference.
     *
     * @param conferenceId  the Conference to add Participant to.
     * @param participantId the Participant to add to the Conference.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} if update was successful,
     * and {@code 204 (NO_CONTENT)} if no update happened.
     */
    @PutMapping("/conference-add/{conferenceId}/{participantId}")
    public ResponseEntity<Void> addParticipantToConference(@PathVariable Long conferenceId, @PathVariable Long participantId) {
        log.info("REST request to add participant {} to conference {}", participantId, conferenceId);
        Integer result = participantService.addParticipantToConference(conferenceId, participantId);
        if (result.equals(0)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * {@code PUT  /conference-remove/:id} : Removes participant from conference.
     *
     * @param id of Participant to remove from conference.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} if update was successful,
     * and {@code 204 (NO_CONTENT)} if no update happened.
     */
    @PutMapping("/conference-remove/{id}")
    public ResponseEntity<Void> removeParticipantFromConference(@PathVariable Long id) {
        log.info("REST request to remove participant {} from conference", id);
        Integer result = participantService.removeParticipantFromConference(id);
        if (result.equals(0)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * {@code DELETE  /delete/by-id/:id} : Deletes a participant record.
     *
     * @param id of the participant to be deleted.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteParticipantById(@PathVariable Long id) {
        log.info("REST request to delete participant: {}", id);
        participantService.deleteParticipantById(id);
        return ResponseEntity.noContent().build();
    }
}

