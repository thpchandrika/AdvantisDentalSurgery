package cs489.apsd.dentalsurgeryapp.service;

import cs489.apsd.dentalsurgeryapp.dto.auth.JwtAuthenticationResponse;
import cs489.apsd.dentalsurgeryapp.dto.auth.SigninRequest;
import cs489.apsd.dentalsurgeryapp.exceptions.UserNotFoundException;

public interface AuthenticationService {

    public JwtAuthenticationResponse login(SigninRequest signinRequest) throws UserNotFoundException;
}
