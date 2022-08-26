package ee.digivikings.conference.service.dto;

import javax.validation.constraints.NotBlank;

/**
 * DTO with authorization information.
 */
public class LoginRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
