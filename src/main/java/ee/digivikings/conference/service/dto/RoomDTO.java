package ee.digivikings.conference.service.dto;

import ee.digivikings.conference.domain.Room;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Room} entity.
 */

public class RoomDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull(message = "Room must have a name.")
    private String name;
    private String location;
    @NotNull(message = "You must specify max seats count to the room.")
    @Min(value = 1, message = "There should be at least 1 spot in the room.")
    private Integer maxSeats;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }
}
