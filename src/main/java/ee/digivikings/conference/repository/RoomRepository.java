package ee.digivikings.conference.repository;

import ee.digivikings.conference.domain.Room;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Room} entity.
 *
 * @author Vassili Moskaljov
 * @version 1.0
 */

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Modifying
    @Query("DELETE FROM Room r WHERE r.id = :id")
    void deleteById(@NotNull Long id);
}
