package cs489.apsd.dentalsurgeryapp.dto.dentist;

public record DentistRequest(
        String dentistID,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String specialization
) {
}
