package ee.digivikings.conference.domain;

import ee.digivikings.conference.domain.enumeration.ConferenceStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * Conference entity.
 */
@Entity
@Table(name = "conference")
public class Conference implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Conference must have a name.")
    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @OneToMany(
            mappedBy = "conference",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Participant> participants;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ConferenceStatus status;

    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.setConference(this);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
        participant.setConference(null);
    }

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

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ConferenceStatus getStatus() {
        return status;
    }

    public void setStatus(ConferenceStatus status) {
        this.status = status;
    }
}
