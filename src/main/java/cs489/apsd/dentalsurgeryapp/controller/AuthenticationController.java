package cs489.apsd.dentalsurgeryapp.controller;

import cs489.apsd.dentalsurgeryapp.dto.auth.JwtAuthenticationResponse;
import cs489.apsd.dentalsurgeryapp.dto.auth.SigninRequest;
import cs489.apsd.dentalsurgeryapp.exceptions.UserNotFoundException;
import cs489.apsd.dentalsurgeryapp.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "an authentication api")
@RestController
@RequestMapping("/api/ads/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public JwtAuthenticationResponse signin(@RequestBody SigninRequest request)
            throws UserNotFoundException, AccessDeniedException {
        return authenticationService.login(request);
    }
}
