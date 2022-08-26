package ee.digivikings.conference.web.rest;

import ee.digivikings.conference.domain.Conference;
import ee.digivikings.conference.service.ConferenceService;
import ee.digivikings.conference.service.dto.ConferenceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing {@link Conference}.
 *
 * @author Vassili Moskaljov.
 * @version 1.0
 */
@PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/conference")
public class ConferenceController {
    private final ConferenceService conferenceService;

    /**
     * {@code GET } : Method to retrieve List of all conferences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body List<{@link Conference}>.
     */
    @GetMapping
    public ResponseEntity<List<ConferenceDTO>> getConference() {
        log.info("REST request to get all conferences");
        return ResponseEntity.ok(conferenceService.getAllConferences());
    }

    /**
     * {@code GET /:id} : Method to retrieve Conference by id.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body {@link Conference}
     * or {@code 204 (NO_CONTENT)} if result is null.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDTO> getConferenceById(@PathVariable Long id) {
        log.info("REST request to get conference by id : {}", id);
        ConferenceDTO result = conferenceService.getById(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET /free-slots/:conferenceId} : Method to retrieve number of free seats for specified conference.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body List<{@link Conference}>
     * or with status {@code 400 (Bad Request)} if the conference does not exist.
     */
    @GetMapping("/free-slots/{conferenceId}")
    public ResponseEntity<Integer> getAvailableSlotsForConference(@PathVariable Long conferenceId) {
        log.info("REST request to get available slots for conference: {}", conferenceId);
        Integer result = conferenceService.getFreeSlotsForConference(conferenceId);
        if (result == -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * {@code POST  /create} : Creates new conference.
     *
     * @param conference the Conference to create.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the created Conference.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ConferenceDTO> createNew(@RequestBody @Valid ConferenceDTO conference) {
        log.info("Rest request to create new conference: {}", conference);
        ConferenceDTO result = conferenceService.createConference(conference);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code PUT  /cancel/:id} : Cancels specified conference.
     *
     * @param id of the conference to be canceled.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} if update was successful,
     * and {@code 204 (NO_CONTENT)} if no update happened.
     */
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelConference(@PathVariable Long id) {
        log.info("Rest request to cancel conference {}", id);
        Integer result = conferenceService.cancelConference(id);
        if (result.equals(0)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * {@code DELETE  /delete/:id} : Deletes a conference record and all participants bound to it.
     *
     * @param id of the conference to be deleted.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        log.info("Rest request to delete conference {}", id);
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }
}
