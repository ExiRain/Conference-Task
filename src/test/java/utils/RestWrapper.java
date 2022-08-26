package utils;

import ee.digivikings.conference.service.dto.LoginRequestDTO;
import io.restassured.http.ContentType;
import utils.services.ConferenceTestService;
import utils.services.ParticipantTestService;
import utils.services.RoomTestService;

import static io.restassured.RestAssured.given;

public class RestWrapper {
    private static final String BASE_URL = "http://localhost:8080/api";

    public RoomTestService roomTestService;
    public ConferenceTestService conferenceTestService;
    public ParticipantTestService participantTestService;

    private RestWrapper(String token) {
        roomTestService = new RoomTestService(token);
        participantTestService = new ParticipantTestService(token);
        conferenceTestService = new ConferenceTestService(token);
    }

    public static RestWrapper loginAs(String username, String password) {
        LoginRequestDTO loginRq = new LoginRequestDTO();
        loginRq.setUsername(username);
        loginRq.setPassword(password);

        String token = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(loginRq)
                .post("/auth/signin")
                .jsonPath().get("accessToken");

        return new RestWrapper(token);
    }
}
