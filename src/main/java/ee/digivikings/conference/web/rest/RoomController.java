package ee.digivikings.conference.web.rest;

import ee.digivikings.conference.service.RoomService;
import ee.digivikings.conference.service.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
@Slf4j
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    /**
     * {@code GET } : Method to retrieve List of all rooms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body List<{@link ee.digivikings.conference.domain.Room}>.
     */
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        log.info("Rest request to get all rooms");
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    /**
     * {@code GET /:id} : Method to retrieve Room by id.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body {@link ee.digivikings.conference.domain.Room}
     * or {@code 204 (NO_CONTENT)} if result is null.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        log.info("REST request to get room by id : {}", id);
        RoomDTO result = roomService.getById(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * {@code POST  /create} : Creates new Room.
     *
     * @param room the Room to create.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the created Room.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody @Valid RoomDTO room) {
        log.info("REST request to create room: {}", room);
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    /**
     * {@code DELETE  /delete/:id} : Deletes a room record.
     *
     * @param id of the room to be deleted.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("REST request to delete room by id: {}", id);
        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }
}
