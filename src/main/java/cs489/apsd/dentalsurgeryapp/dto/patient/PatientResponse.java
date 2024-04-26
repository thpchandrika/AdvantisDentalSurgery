package cs489.apsd.dentalsurgeryapp.dto.patient;

import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.domain.Appointment;

import java.time.LocalDate;
import java.util.List;

public record PatientResponse(
        Integer patientId,
        String patientNumber,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        LocalDate dob,

        Address mailingAddress
) {
}
