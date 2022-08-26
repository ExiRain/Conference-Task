package utils.services;

import ee.digivikings.conference.service.dto.RoomDTO;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RoomTestService extends RestService {
    public RoomTestService(String token) {
        super(token);
    }

    @Override
    protected String getBasePath() {
        return "/v1/room";
    }

    public RoomDTO createRoom(RoomDTO room) {
        return given()
                .spec(REQ_SPEC)
                .accept(ContentType.JSON)
                .body(room)
                .post("/create")
                .getBody()
                .as(RoomDTO.class);
    }

    public List<RoomDTO> getRooms() {
        return given().spec(REQ_SPEC)
                .get("/")
                .jsonPath().getList(".", RoomDTO.class);
    }

    public RoomDTO getById(Long id) {
        return given().spec(REQ_SPEC)
                .accept(ContentType.JSON)
                .get("/" + id)
                .getBody()
                .as(RoomDTO.class);
    }

    public Integer deleteById(Long id) {
        return given().spec(REQ_SPEC)
                .delete("/delete/" + id)
                .getStatusCode();
    }
}
