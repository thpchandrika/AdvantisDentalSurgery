package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.User;
import cs489.apsd.dentalsurgeryapp.dto.auth.JwtAuthenticationResponse;
import cs489.apsd.dentalsurgeryapp.dto.auth.SigninRequest;
import cs489.apsd.dentalsurgeryapp.exceptions.UserNotFoundException;
import cs489.apsd.dentalsurgeryapp.service.AuthenticationService;
import cs489.apsd.dentalsurgeryapp.service.UserService;
import cs489.apsd.dentalsurgeryapp.util.JwtUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtilityService jwtUtilityService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse login(SigninRequest request) throws UserNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        User user = userService.findByUsername(request.username());
//                .orElseThrow(() -> new UserNotFoundException("Invalid email or password."));
        String jwt = jwtUtilityService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt);
        return jwtAuthenticationResponse;
//        JwtAuthenticationResponse userAuthResponse = null;
//        try {
//            var username = request.username();
//            var password = request.password();
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username,
//                            password)
//            );
//
//            var user = userService.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User not found"));
//            if(user != null) {
//                var jwtToken = jwtUtilityService.generateToken(user);
//                userAuthResponse = new JwtAuthenticationResponse(jwtToken);
//            }
//        } catch (Exception ex) {
//            System.out.println("UserAuthException is: " + ex);
//            throw ex;
//        }
//       return  userAuthResponse;
//    }
    }
}
