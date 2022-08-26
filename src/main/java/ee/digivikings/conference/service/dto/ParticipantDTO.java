package ee.digivikings.conference.service.dto;

import ee.digivikings.conference.domain.Participant;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Participant} entity.
 */

public class ParticipantDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Participant must have an email.")
    @Length(min = 6, message = "Email must be at least 6 characters long.")
    private String email;
    @NotBlank(message = "Participant must have a name.")
    @Length(min = 2, message = "Full name must have at least 2 characters.")
    private String fullName;
    private Long conferenceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Override
    public String toString() {
        return "ParticipantDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", conferenceId=" + conferenceId +
                '}';
    }
}
