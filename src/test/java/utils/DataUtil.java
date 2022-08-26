package utils;

import ee.digivikings.conference.domain.enumeration.ConferenceStatus;
import ee.digivikings.conference.service.dto.ConferenceDTO;
import ee.digivikings.conference.service.dto.ParticipantDTO;
import ee.digivikings.conference.service.dto.RoomDTO;

public class DataUtil {
    private static final String PARTICIPANT_NAME = "Participant Name";
    private static final String PARTICIPANT_EMAIL = "participant@email.com";
    private static final String CONFERENCE_NAME = "Conference name";
    private static final String CONFERENCE_CITY = "Conference city";
    private static final String ROOM_NAME = "Room 406";
    private static final String ROOM_LOCATION = "Tallinn";
    private static final Integer ROOM_MAX_SEATS = 5;

    public static RoomDTO getRoomDTO() {
        RoomDTO result = new RoomDTO();
        result.setLocation(ROOM_LOCATION);
        result.setName(ROOM_NAME);
        result.setMaxSeats(ROOM_MAX_SEATS);
        return result;
    }

    public static ParticipantDTO getParticipantDTO() {
        ParticipantDTO result = new ParticipantDTO();
        result.setEmail(PARTICIPANT_EMAIL);
        result.setFullName(PARTICIPANT_NAME);
        result.setConferenceId(null);
        return result;
    }

    public static ConferenceDTO getConferenceDTO(Long roomId) {
        ConferenceDTO result = new ConferenceDTO();
        result.setCity(CONFERENCE_CITY);
        result.setName(CONFERENCE_NAME);
        result.setRoomId(roomId);
        return result;
    }
}
