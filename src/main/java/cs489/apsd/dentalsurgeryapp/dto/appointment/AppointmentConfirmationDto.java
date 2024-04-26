package cs489.apsd.dentalsurgeryapp.dto.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentConfirmationDto (
        String to,
        LocalDate appointmentDate,
        LocalTime appointmentTime,

        String dentist,
        String patient,

        String surgeryLocation
){
}
