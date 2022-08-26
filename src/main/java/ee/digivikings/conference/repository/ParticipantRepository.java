package ee.digivikings.conference.repository;

import ee.digivikings.conference.domain.Participant;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Participant} entity.
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Modifying
    @Query("UPDATE Participant p SET p.conference.id = :newId WHERE p.id = :participantId")
    Integer updateConferenceId(Long newId, Long participantId);

    @Modifying
    @Query("DELETE FROM Participant p WHERE p.id = :id")
    void deleteById(@NotNull Long id);
}
