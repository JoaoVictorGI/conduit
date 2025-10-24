package com.conduit.user;

import com.conduit.user.dto.UserDTO;
import com.conduit.user.dto.response.UserWithoutIdAndPasswordResponse;

public class UserMapper {
    static UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getEmail(), null, user.getUsername(), user.getBio(), user.getImage());
    }

    static UserWithoutIdAndPasswordResponse toWithoutIdAndPasswordDto(User user) {
        return new UserWithoutIdAndPasswordResponse(new UserWithoutIdAndPasswordResponse.UserData(
                user.getEmail(), null, user.getUsername(), user.getBio(), user.getImage()));
    }
}
