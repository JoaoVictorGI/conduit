package com.conduit.user;

import com.conduit.user.dto.request.LoginUserRequestDTO;
import com.conduit.user.dto.request.RegisterUserRequestDTO;
import com.conduit.user.dto.response.UserWithoutIdAndPasswordResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class UserController {
    private final UserService service;
    private final AuthenticationManager authManager;
    private final JwtTokenHelper jwtTokenHelper;

    public UserController(UserService service, AuthenticationManager authManager, JwtTokenHelper jwtTokenHelper) {
        this.service = service;
        this.authManager = authManager;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @PostMapping
    ResponseEntity<UserWithoutIdAndPasswordResponse> registerUser(@RequestBody @Valid RegisterUserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerUser(dto));
    }

    @PostMapping("/login")
    ResponseEntity<UserWithoutIdAndPasswordResponse> loginUser(@RequestBody @Valid LoginUserRequestDTO dto) {
        return ResponseEntity.ok(service.loginUser(dto));
    }
}
