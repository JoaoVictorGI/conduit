package com.conduit.user.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequestDTO(@Valid UserData user
) {
    public record UserData(
            @NotBlank(message = "Username is required") String username,
            @Email(message = "Invalid e-mail address") String email,
            @NotBlank(message = "Password is required") String password) {
    }
}
