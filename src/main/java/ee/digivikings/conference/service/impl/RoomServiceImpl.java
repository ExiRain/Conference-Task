package ee.digivikings.conference.service.impl;

import ee.digivikings.conference.domain.Room;
import ee.digivikings.conference.repository.RoomRepository;
import ee.digivikings.conference.service.RoomService;
import ee.digivikings.conference.service.dto.RoomDTO;
import ee.digivikings.conference.service.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Room}.
 *
 * @author Vassili Moskaljov.
 * @version 1.0
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public List<RoomDTO> getAllRooms() {
        log.debug("Getting all rooms");
        return roomMapper.toDto(roomRepository.findAll());
    }

    @Override
    public RoomDTO getById(Long id) {
        log.debug("Fetching room by id: {}", id);
        Optional<Room> room = roomRepository.findById(id);
        return room.map(roomMapper::toDto).orElse(null);
    }

    @Override
    public RoomDTO createRoom(RoomDTO room) {
        log.debug("Creating new Room: {}", room);
        Room createdRoom = roomRepository.save(roomMapper.toEntity(room));
        return roomMapper.toDto(createdRoom);
    }

    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }
}
