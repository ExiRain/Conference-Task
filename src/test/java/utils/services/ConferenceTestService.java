package utils.services;

import ee.digivikings.conference.service.dto.ConferenceDTO;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ConferenceTestService extends RestService {
    public ConferenceTestService(String token) {
        super(token);
    }

    @Override
    protected String getBasePath() {
        return "/v1/conference";
    }

    public ConferenceDTO createConference(ConferenceDTO conference) {
        return given().spec(REQ_SPEC).body(conference).post("/create").as(ConferenceDTO.class);
    }

    public List<ConferenceDTO> getConferences() {
        return given().spec(REQ_SPEC)
                .get("/")
                .jsonPath().getList(".", ConferenceDTO.class);
    }

    public Integer getFreeSlots(Long id) {
        return given().spec(REQ_SPEC)
                .get("/free-slots/" + id)
                .as(Integer.class);
    }

    public Integer cancelConference(Long id) {
        return given().spec(REQ_SPEC)
                .put("/cancel/" + id)
                .getStatusCode();
    }

    public ConferenceDTO getById(Long id) {
        return given().spec(REQ_SPEC)
                .get("/" + id)
                .as(ConferenceDTO.class);
    }

    public Integer deleteById(Long id) {
        return given().spec(REQ_SPEC)
                .delete("/delete/" + id)
                .getStatusCode();
    }
}
