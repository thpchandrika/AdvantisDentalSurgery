package cs489.apsd.dentalsurgeryapp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record JwtAuthenticationResponse(String token) {
}
