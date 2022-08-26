package ee.digivikings.conference.service.mapper;

import ee.digivikings.conference.domain.Conference;
import ee.digivikings.conference.service.dto.ConferenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Conference} and its DTO {@link ConferenceDTO}.
 *
 * @author Vassili Moskaljov.
 * @version 1.0
 */

@Mapper(componentModel = "spring", uses = {ParticipantMapper.class, RoomMapper.class})
public interface ConferenceMapper extends EntityMapper<ConferenceDTO, Conference> {
    @Mapping(source = "room.id", target = "roomId")
    ConferenceDTO toDto(Conference conference);

    @Mapping(source = "roomId", target = "room")
    Conference toEntity(ConferenceDTO conferenceDTO);

    default Conference fromId(Long id) {
        if (id == null) {
            return null;
        }
        Conference conference = new Conference();
        conference.setId(id);
        return conference;
    }
}
