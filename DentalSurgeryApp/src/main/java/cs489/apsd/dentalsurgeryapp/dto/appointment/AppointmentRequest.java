package cs489.apsd.dentalsurgeryapp.dto.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequest(LocalDate appointmentDate,
                                 LocalTime appointmentTime,
                                 Integer dentistId,
                                 Integer patientId,
                                 Integer surgeryId,
                                 String status) {
}
