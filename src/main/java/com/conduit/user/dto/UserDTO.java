package com.conduit.user.dto;

import java.util.UUID;

public record UserDTO(UUID id,
                      String email,
                      String token,
                      String username,
                      String bio,
                      String image) {
}
