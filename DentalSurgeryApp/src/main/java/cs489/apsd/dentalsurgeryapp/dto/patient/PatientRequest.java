package cs489.apsd.dentalsurgeryapp.dto.patient;

import cs489.apsd.dentalsurgeryapp.domain.Address;

import java.time.LocalDate;

public record PatientRequest(
         String patientNumber,
         String firstName,
         String lastName,
         String phoneNumber,
         String email,
         LocalDate dob,

         Address mailingAddress
) {
}
