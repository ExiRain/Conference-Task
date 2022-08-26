package ee.digivikings.conference.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Participant entity that contains information Person information attending the conference.
 */

@Entity
@Table(name = "participant")
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Participant must have an email.")
    @Length(min = 6, message = "Email must be at least 6 characters long.")
    private String email;

    @NotBlank(message = "Participant must have a name.")
    @Length(min = 2, message = "Full name must have at least 2 characters.")
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conference conference;

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        return id != null && id.equals(((Participant) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
