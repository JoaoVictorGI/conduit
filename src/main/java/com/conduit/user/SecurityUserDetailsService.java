package com.conduit.user;

import com.conduit.user.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public SecurityUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(this::toSecurityUser)
                .orElseThrow(() -> new UserNotFoundException("Failed to retrieve user with email: " + username));
    }

    private SecurityUser toSecurityUser(User user) {
        return new SecurityUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}
