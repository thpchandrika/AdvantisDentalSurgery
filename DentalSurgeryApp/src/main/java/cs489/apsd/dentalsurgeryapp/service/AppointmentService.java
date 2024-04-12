package cs489.apsd.dentalsurgeryapp.service;

import cs489.apsd.dentalsurgeryapp.domain.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> addAppointmentList(List<Appointment> appointments);
    List<Appointment> getAllAppointments();
}
