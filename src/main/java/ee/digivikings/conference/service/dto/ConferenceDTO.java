package ee.digivikings.conference.service.dto;

import ee.digivikings.conference.domain.Conference;
import ee.digivikings.conference.domain.enumeration.ConferenceStatus;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Conference} entity.
 */

public class ConferenceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Conference must have a name.")
    private String name;
    private String city;
    private List<ParticipantDTO> participants;
    private Long roomId;
    private ConferenceStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public ConferenceStatus getStatus() {
        return status;
    }

    public void setStatus(ConferenceStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConferenceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", participants=" + participants +
                ", roomId=" + roomId +
                ", status=" + status +
                '}';
    }
}
