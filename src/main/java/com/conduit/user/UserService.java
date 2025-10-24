package com.conduit.user;

import com.conduit.user.dto.request.LoginUserRequestDTO;
import com.conduit.user.dto.request.RegisterUserRequestDTO;
import com.conduit.user.dto.response.UserWithoutIdAndPasswordResponse;
import com.conduit.user.exception.DuplicateEmailException;
import com.conduit.user.exception.InvalidCredentialsException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtTokenHelper jwtTokenHelper;

    public UserService(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authManager,
            JwtTokenHelper jwtTokenHelper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Transactional
    UserWithoutIdAndPasswordResponse registerUser(RegisterUserRequestDTO dto) {
        if (repository.findByEmail(dto.user().email()).isPresent()) {
            throw new DuplicateEmailException(
                    "E-mail already in use: " + dto.user().email());
        }

        var user = new User();
        user.setUsername(dto.user().username());
        user.setEmail(dto.user().email());
        user.setPassword(passwordEncoder.encode(dto.user().password()));

        return UserMapper.toWithoutIdAndPasswordDto(repository.save(user));
    }

    UserWithoutIdAndPasswordResponse loginUser(LoginUserRequestDTO dto) {
        var authentication = new UsernamePasswordAuthenticationToken(
                dto.user().email(), dto.user().password());
        authManager.authenticate(authentication);

        var user = repository
                .findByEmail(dto.user().email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid e-mail or password"));

        var jwtToken = jwtTokenHelper.generateToken(UserMapper.toDto(user));
        return new UserWithoutIdAndPasswordResponse(new UserWithoutIdAndPasswordResponse.UserData(
                user.getEmail(), jwtToken, user.getUsername(), user.getBio(), user.getImage()));
    }
}
