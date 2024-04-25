package cs489.apsd.dentalsurgeryapp.service;

import cs489.apsd.dentalsurgeryapp.domain.Appointment;
import cs489.apsd.dentalsurgeryapp.dto.appointment.AppointmentRequest;
import cs489.apsd.dentalsurgeryapp.exceptions.AppointmentNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.PatientNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.SurgeryNotFoundException;

import java.util.List;

public interface AppointmentService {
    List<Appointment> addAppointmentList(List<Appointment> appointments);
    List<Appointment> getAllAppointments();
    List<Appointment> getDentistsAppointments(Integer doctorId);
    List<Appointment> getPatientsAppointments(Integer patientId);
    Appointment bookAppointment(AppointmentRequest appointmentRequest) throws PatientNotFoundException, DentistNotFoundException, SurgeryNotFoundException;

    Appointment requestAppointment(AppointmentRequest appointmentRequest) throws PatientNotFoundException, DentistNotFoundException, SurgeryNotFoundException;

    Appointment changeAppointmentStatus(Integer id, String status) throws AppointmentNotFoundException;

    Appointment updateAppointment(Integer id, AppointmentRequest appointmentRequest) throws PatientNotFoundException, DentistNotFoundException, SurgeryNotFoundException, AppointmentNotFoundException;
}
