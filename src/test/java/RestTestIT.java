import ee.digivikings.conference.domain.enumeration.ConferenceStatus;
import ee.digivikings.conference.service.dto.ConferenceDTO;
import ee.digivikings.conference.service.dto.ParticipantDTO;
import ee.digivikings.conference.service.dto.RoomDTO;
import org.junit.jupiter.api.*;
import utils.DataUtil;
import utils.RestWrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestTestIT {
    private static Long participantId;
    private static Long conferenceId;
    private static Long roomId;
    private static RestWrapper api;


    @BeforeAll
    public static void prepareClient() {
        api = RestWrapper.loginAs("admin", "admin");
    }

    @Test
    @Order(1)
    @DisplayName("Participant create test")
    void createParticipant() {
        int beforeCreateSize = api.participantTestService.getParticipants().size();

        ParticipantDTO participant = DataUtil.getParticipantDTO();
        ParticipantDTO createdParticipant = api.participantTestService.createParticipant(participant);
        participantId = createdParticipant.getId();

        int afterCreateSize = api.participantTestService.getParticipants().size();

        assertEquals(++beforeCreateSize, afterCreateSize);
        assertEquals(participant.getEmail(), createdParticipant.getEmail());
        assertEquals(participant.getFullName(), createdParticipant.getFullName());
    }

    @Test
    @Order(2)
    @DisplayName("Room create test")
    void createRoom() {
        int beforeCreateSize = api.roomTestService.getRooms().size();

        RoomDTO room = DataUtil.getRoomDTO();
        RoomDTO createdRoom = api.roomTestService.createRoom(room);
        roomId = createdRoom.getId();

        int afterCreateSize = api.roomTestService.getRooms().size();

        assertEquals(beforeCreateSize + 1, afterCreateSize);
        assertEquals(room.getMaxSeats(), createdRoom.getMaxSeats());
        assertEquals(room.getLocation(), createdRoom.getLocation());
        assertEquals(room.getName(), createdRoom.getName());
    }

    @Test
    @Order(3)
    @DisplayName("Conference create test")
    void createConference() {
        int beforeCreateSize = api.conferenceTestService.getConferences().size();

        ConferenceDTO conference = DataUtil.getConferenceDTO(roomId);
        ConferenceDTO createdConference = api.conferenceTestService.createConference(conference);
        conferenceId = createdConference.getId();

        int afterCreateSize = api.conferenceTestService.getConferences().size();

        assertEquals(beforeCreateSize + 1, afterCreateSize);
        assertEquals(conference.getName(), createdConference.getName());
        assertEquals(roomId, createdConference.getRoomId());
        assertEquals(conference.getCity(), createdConference.getCity());
        assertEquals(ConferenceStatus.ACTIVE, createdConference.getStatus());
    }

    @Test
    @Order(4)
    @DisplayName("Get created room")
    void getRoom() {
        RoomDTO room = DataUtil.getRoomDTO();
        RoomDTO createdRoom = api.roomTestService.getById(roomId);

        assertEquals(room.getName(), createdRoom.getName());
        assertEquals(room.getMaxSeats(), createdRoom.getMaxSeats());
        assertEquals(room.getLocation(), createdRoom.getLocation());
    }

    @Test
    @Order(5)
    @DisplayName("Add participant to conference")
    void addParticipantToConference() {
        Integer actual = api.participantTestService.addToConference(conferenceId, participantId);
        assertEquals(200, actual);
    }

    @Test
    @Order(6)
    @DisplayName("Remove participant from conference")
    void removeParticipantFromConference() {
        Integer actual = api.participantTestService.removeFromConference(participantId);
        assertEquals(200, actual);
    }

    @Test
    @Order(7)
    @DisplayName("Cancel conference")
    void cancelConference() {
        Integer actual = api.conferenceTestService.cancelConference(conferenceId);
        ConferenceDTO conference = api.conferenceTestService.getById(conferenceId);

        assertEquals(200, actual);
        assertEquals(ConferenceStatus.CANCELED, conference.getStatus());
    }

    @Test
    @Order(8)
    @DisplayName("Get free slots for conference")
    void getFreeSlots() {
        Integer actual = api.conferenceTestService.getFreeSlots(conferenceId);
        assertEquals(DataUtil.getRoomDTO().getMaxSeats(), actual);
    }

    @Test
    @Order(9)
    @DisplayName("Delete Participant")
    void deleteParticipants() {
        Integer actual = api.participantTestService.deleteById(participantId);
        assertEquals(204, actual);
    }

    @Test
    @Order(10)
    @DisplayName("Delete Room")
    void deleteRoom() {
        Integer actual = api.roomTestService.deleteById(roomId);
        assertEquals(204, actual);
    }

    @Test
    @Order(11)
    @DisplayName("Delete Conference")
    void deleteConference() {
        Integer actual = api.conferenceTestService.deleteById(conferenceId);
        assertEquals(204, actual);
    }
}
