package com.conduit.user.dto.response;

public record UserWithoutIdAndPasswordResponse(UserData user) {
    public record UserData(String email, String token, String username, String bio, String image) {}
}
