package ee.digivikings.conference.repository;

import ee.digivikings.conference.domain.Conference;
import ee.digivikings.conference.domain.enumeration.ConferenceStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for {@link Conference} entity.
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    @Override
    List<Conference> findAll();

    @Modifying
    @Query("UPDATE Conference c SET c.status = :status WHERE c.id = :id")
    Integer updateConferenceStatus(Long id, ConferenceStatus status);

    @Modifying
    @Query("DELETE FROM Conference c WHERE c.id = :id")
    void deleteById(@NotNull Long id);
}
