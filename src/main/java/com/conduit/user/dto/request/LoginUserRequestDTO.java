package com.conduit.user.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserRequestDTO(@Valid UserData user) {
    public record UserData(
            @Email(message = "Invalid e-mail address") String email,
            @NotBlank(message = "Password is required") String password) {
    }
}
