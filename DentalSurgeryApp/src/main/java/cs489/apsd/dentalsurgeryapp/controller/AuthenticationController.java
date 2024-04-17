package cs489.apsd.dentalsurgeryapp.controller;

import cs489.apsd.dentalsurgeryapp.dto.auth.JwtAuthenticationResponse;
import cs489.apsd.dentalsurgeryapp.dto.auth.SigninRequest;
import cs489.apsd.dentalsurgeryapp.exceptions.UserNotFoundException;
import cs489.apsd.dentalsurgeryapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adsweb/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public JwtAuthenticationResponse signin(@RequestBody SigninRequest request) throws UserNotFoundException {
        return authenticationService.login(request);
    }

    @GetMapping("/all")
    public ResponseEntity<String> all() {
        return new ResponseEntity<>("Hello all", HttpStatus.OK);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('OFFICEMANAGER')")
    public ResponseEntity<String> admin() {
        return new ResponseEntity<>("Hello admin", HttpStatus.OK);
    }
}
