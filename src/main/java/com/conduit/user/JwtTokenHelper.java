package com.conduit.user;

import com.conduit.ApplicationProperties;
import com.conduit.user.dto.UserDTO;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
class JwtTokenHelper {
    private final JwtEncoder encoder;
    private final ApplicationProperties properties;

    public JwtTokenHelper(JwtEncoder encoder, ApplicationProperties properties) {
        this.encoder = encoder;
        this.properties = properties;
    }

    public String generateToken(UserDTO dto) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(properties.jwt().issuer())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(properties.jwt().expiresAt()))
                .subject(dto.email())
                .claim("user_id", dto.id())
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
