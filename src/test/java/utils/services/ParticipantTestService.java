package utils.services;

import ee.digivikings.conference.service.dto.ParticipantDTO;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ParticipantTestService extends RestService {
    public ParticipantTestService(String token) {
        super(token);
    }

    @Override
    protected String getBasePath() {
        return "/v1/participant";
    }

    public ParticipantDTO createParticipant(ParticipantDTO participant) {
        return given()
                .spec(REQ_SPEC)
                .accept(ContentType.JSON)
                .body(participant)
                .post("/create")
                .getBody()
                .as(ParticipantDTO.class);
    }

    public List<ParticipantDTO> getParticipants() {
        return given().spec(REQ_SPEC)
                .get("/")
                .jsonPath().getList(".", ParticipantDTO.class);
    }

    public ParticipantDTO getById(Long id) {
        return given().spec(REQ_SPEC)
                .get("/" + id)
                .as(ParticipantDTO.class);
    }

    public Integer deleteById(Long id) {
        return given().spec(REQ_SPEC)
                .delete("/delete/" + id)
                .getStatusCode();
    }

    public Integer addToConference(Long conferenceId, Long participantId) {
        return given().spec(REQ_SPEC)
                .put("/conference-add/" + conferenceId + "/" + participantId)
                .getStatusCode();
    }

    public Integer removeFromConference(Long participantId) {
        return given().spec(REQ_SPEC)
                .put("/conference-remove/" + participantId)
                .getStatusCode();
    }
}
