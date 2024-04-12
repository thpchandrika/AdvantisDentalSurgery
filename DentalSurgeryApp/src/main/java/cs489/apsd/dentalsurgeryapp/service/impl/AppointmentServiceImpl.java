package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.Appointment;
import cs489.apsd.dentalsurgeryapp.repository.AppointmentRepository;
import cs489.apsd.dentalsurgeryapp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public List<Appointment> addAppointmentList(List<Appointment> appointments) {
        return appointmentRepository.saveAll(appointments);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
