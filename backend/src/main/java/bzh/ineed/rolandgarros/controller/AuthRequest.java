package bzh.ineed.rolandgarros.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthRequest {
    @NotNull
    @Email
    @Size(min = 5, max = 50)
    private String username;

    @NotNull
    @Size(min = 5, max = 10)
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
