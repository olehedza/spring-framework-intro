package spring.intro.dto;

import spring.intro.model.User;

public class UserResponseDto {
    private String email;
    private String password;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
