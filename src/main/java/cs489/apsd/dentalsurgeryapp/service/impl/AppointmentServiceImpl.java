package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.constant.AppointmentStatus;
import cs489.apsd.dentalsurgeryapp.domain.Appointment;
import cs489.apsd.dentalsurgeryapp.dto.appointment.AppointmentConfirmationDto;
import cs489.apsd.dentalsurgeryapp.dto.appointment.AppointmentRequest;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientResponse;
import cs489.apsd.dentalsurgeryapp.exceptions.AppointmentNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.PatientNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.SurgeryNotFoundException;
import cs489.apsd.dentalsurgeryapp.repository.AppointmentRepository;
import cs489.apsd.dentalsurgeryapp.repository.DentistRepository;
import cs489.apsd.dentalsurgeryapp.repository.PatientRepository;
import cs489.apsd.dentalsurgeryapp.repository.SurgeryRepository;
import cs489.apsd.dentalsurgeryapp.service.AppointmentService;
import cs489.apsd.dentalsurgeryapp.service.DentistService;
import cs489.apsd.dentalsurgeryapp.service.PatientService;
import cs489.apsd.dentalsurgeryapp.service.SurgeryService;
import cs489.apsd.dentalsurgeryapp.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Appointment> addAppointmentList(List<Appointment> appointments) {
        return appointmentRepository.saveAll(appointments);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getDentistsAppointments(Integer dentistId) {
        return appointmentRepository.findByDentistId(dentistId);
    }

    @Override
    public List<Appointment> getPatientsAppointments(Integer patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public Appointment bookAppointment(AppointmentRequest appointmentRequest) throws DentistNotFoundException, PatientNotFoundException, SurgeryNotFoundException {
       var bookedAppointment = saveAppointment(appointmentRequest, AppointmentStatus.APPROVED.toString());
       return bookedAppointment;
    }

    @Override
    public Appointment requestAppointment(AppointmentRequest appointmentRequest) throws PatientNotFoundException, DentistNotFoundException, SurgeryNotFoundException {
        return saveAppointment(appointmentRequest, AppointmentStatus.PENDING.toString());
    }

    @Override
    public Appointment changeAppointmentStatus(Integer id, String status) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new AppointmentNotFoundException(
                        String.format("Appointment with id %d not found", id)
                ));
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Integer id, AppointmentRequest appointmentRequest) throws PatientNotFoundException, DentistNotFoundException, SurgeryNotFoundException, AppointmentNotFoundException {
        var patient = patientRepository.findById(appointmentRequest.patientId())
                .orElseThrow(()->new PatientNotFoundException(
                        String.format("Patient with id %d not found", appointmentRequest.patientId())
                ));
        var dentist = dentistRepository.findById(appointmentRequest.dentistId())
                .orElseThrow(()->new DentistNotFoundException(
                        String.format("Dentist with id %d not found", appointmentRequest.dentistId())
                ));
        var surgery = surgeryRepository.findById(appointmentRequest.surgeryId())
                .orElseThrow(()->new SurgeryNotFoundException(
                        String.format("Surgery with id %d not found", appointmentRequest.surgeryId())
                ));
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(
                        String.format("Appointment with id %d not found", id)
                ));
        existingAppointment.setAppointmentDate(appointmentRequest.appointmentDate());
        existingAppointment.setAppointmentTime(appointmentRequest.appointmentTime());
        existingAppointment.setPatient(patient);
        existingAppointment.setDentist(dentist);
        existingAppointment.setSurgery(surgery);
        return appointmentRepository.save(existingAppointment);
    }

    private Appointment saveAppointment(AppointmentRequest appointmentRequest, String appointmentStatus) throws PatientNotFoundException, DentistNotFoundException, SurgeryNotFoundException {
        var patient = patientRepository.findById(appointmentRequest.patientId())
                .orElseThrow(()->new PatientNotFoundException(
                        String.format("Patient with id %d not found", appointmentRequest.patientId())
                ));
        var dentist = dentistRepository.findById(appointmentRequest.dentistId())
                .orElseThrow(()->new DentistNotFoundException(
                        String.format("Dentist with id %d not found", appointmentRequest.dentistId())
                ));
        var surgery = surgeryRepository.findById(appointmentRequest.surgeryId())
                .orElseThrow(()->new SurgeryNotFoundException(
                        String.format("Surgery with id %d not found", appointmentRequest.surgeryId())
                ));
        Appointment appointment = new Appointment(appointmentRequest.appointmentDate(), appointmentRequest.appointmentTime(), appointmentStatus);
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setSurgery(surgery);
        var confirmationDto = new AppointmentConfirmationDto(patient.getEmail(),
                appointmentRequest.appointmentDate(),
                appointmentRequest.appointmentTime(),
                dentist.getFirstName() + " " + dentist.getLastName(),
                patient.getFirstName() + " " + patient.getLastName(),
                surgery.getLocationAddress().toString()
                );
//        emailService.sendAppointmentConfirmationEmail(confirmationDto);
        return appointmentRepository.save(appointment);
    }

    private void sendAppointmentConfirmationEmail(AppointmentConfirmationDto confirmationDto){
        emailService.sendAppointmentConfirmationEmail(confirmationDto);
    }
}
