package ee.digivikings.conference.service;

import ee.digivikings.conference.service.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    /**
     * Get all Rooms.
     *
     * @return list of all rooms.
     */
    List<RoomDTO> getAllRooms();

    /**
     * Get Room by specified ID.
     *
     * @param id of room to find.
     * @return room the by specified id.
     */
    RoomDTO getById(Long id);

    /**
     * Create new room.
     *
     * @param room - Room to be created.
     * @return created Room.
     */
    RoomDTO createRoom(RoomDTO room);

    /**
     * Delete Room by id.
     *
     * @param id of the Room to be deleted.
     */
    void deleteRoomById(Long id);
}
