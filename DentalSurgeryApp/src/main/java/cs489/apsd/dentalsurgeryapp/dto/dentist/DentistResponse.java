package cs489.apsd.dentalsurgeryapp.dto.dentist;

public record DentistResponse(
        Integer id,
        String dentistID,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String specialization
) {
}
