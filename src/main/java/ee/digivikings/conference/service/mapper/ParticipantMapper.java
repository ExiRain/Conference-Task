package ee.digivikings.conference.service.mapper;

import ee.digivikings.conference.domain.Participant;
import ee.digivikings.conference.service.dto.ParticipantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Participant} and its DTO {@link ParticipantDTO}.
 *
 * @author Vassili Moskaljov.
 * @version 1.0
 */

@Mapper(componentModel = "spring")
public interface ParticipantMapper extends EntityMapper<ParticipantDTO, Participant> {
    @Mapping(source = "conference.id", target = "conferenceId")
    ParticipantDTO toDto(Participant participant);

    @Mapping(source = "conferenceId", target = "conference.id")
    Participant toEntity(ParticipantDTO participantDTO);

    default Participant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Participant participant = new Participant();
        participant.setId(id);
        return participant;
    }
}
