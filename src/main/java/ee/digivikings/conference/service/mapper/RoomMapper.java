package ee.digivikings.conference.service.mapper;

import ee.digivikings.conference.domain.Room;
import ee.digivikings.conference.service.dto.ParticipantDTO;
import ee.digivikings.conference.service.dto.RoomDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Room} and its DTO {@link ParticipantDTO}.
 *
 * @author Vassili Moskaljov.
 * @version 1.0
 */

@Mapper(componentModel = "spring")
public interface RoomMapper extends EntityMapper<RoomDTO, Room> {
    Room toEntity(RoomDTO roomDTO);

    RoomDTO toDto(Room room);

    default Room fromId(Long id) {
        if (id == null) {
            return null;
        }
        Room room = new Room();
        room.setId(id);
        return room;
    }
}
