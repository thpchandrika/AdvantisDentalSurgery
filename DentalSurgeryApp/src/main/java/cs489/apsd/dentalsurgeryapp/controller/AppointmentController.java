package cs489.apsd.dentalsurgeryapp.controller;

import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.domain.Appointment;
import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import cs489.apsd.dentalsurgeryapp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public ResponseEntity<List<Appointment>> getAllAppointment(){
        var appointments =  appointmentService.getAllAppointments();
        var response = new ResponseDto(true,
                appointments,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment){
        var addedAppointment =  appointmentService.bookAppointment(appointment);
        var response = new ResponseDto(true,
                addedAppointment,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(addedAppointment, HttpStatus.OK);
    }
}
