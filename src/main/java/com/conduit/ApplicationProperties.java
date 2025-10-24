package com.conduit;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app")
@Validated
public record ApplicationProperties(JwtProperties jwt) {
    public record JwtProperties(
            @DefaultValue("Conduit") String issuer,
            @DefaultValue("604800") Long expiresAt,
            RSAPublicKey publicKey,
            RSAPrivateKey privateKey) {}
}
