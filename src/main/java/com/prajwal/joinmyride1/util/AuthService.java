

package com.prajwal.joinmyride1.util;

import com.prajwal.joinmyride1.Repository.UserRepository;
import com.prajwal.joinmyride1.dto.request.AuthenticationRequest;
import com.prajwal.joinmyride1.dto.response.AuthenticationResponse;
import com.prajwal.joinmyride1.entity.User;
import com.prajwal.joinmyride1.request.CreateUserRequest;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth"})
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService() {
    }

    @PostMapping({"/register"})
    public AuthenticationResponse RegisterUser(@RequestBody CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        String jwtToken = this.jwtUtil.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }

    @PostMapping({"/login"})
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException var5) {
            throw new RuntimeException("Invalid username/password");
        }

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
            return new RuntimeException("User not found");
        });
        String jwtToken = jwtUtil.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }
}

