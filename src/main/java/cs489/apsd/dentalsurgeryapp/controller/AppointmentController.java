package cs489.apsd.dentalsurgeryapp.controller;

import cs489.apsd.dentalsurgeryapp.constant.AppointmentStatus;
import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.domain.Appointment;
import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import cs489.apsd.dentalsurgeryapp.dto.appointment.AppointmentRequest;
import cs489.apsd.dentalsurgeryapp.exceptions.AppointmentNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.PatientNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.SurgeryNotFoundException;
import cs489.apsd.dentalsurgeryapp.service.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Appointment", description = "an appointment api")
@RestController
@RequestMapping("api/ads/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = {"","/"})
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> getAllAppointment(){
        var appointments =  appointmentService.getAllAppointments();
        var response = new ResponseDto(true,
                appointments,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/appointmentofdentist/{dentistId}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER') | hasAuthority('DENTIST')")
    public ResponseEntity<ResponseDto> getAppointmentsOfDentist(@PathVariable @NotNull Integer dentistId){
        var appointments =  appointmentService.getDentistsAppointments(dentistId);
        var response = new ResponseDto(true,
                appointments,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/appointmentofpatient/{patientId}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER') | hasAuthority('PATIENT')")
    public ResponseEntity<ResponseDto> getAppointmentsOfPatient(@PathVariable @NotNull Integer patientId){
        var appointments =  appointmentService.getPatientsAppointments(patientId);
        var response = new ResponseDto(true,
                appointments,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/book")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> bookAppointment(@RequestBody AppointmentRequest appointmentRequest)
            throws SurgeryNotFoundException, DentistNotFoundException, PatientNotFoundException {
        var addedAppointment =  appointmentService.bookAppointment(appointmentRequest);
        var response = new ResponseDto(true,
                addedAppointment,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/request")
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<ResponseDto> requestAppointment(@RequestBody AppointmentRequest appointmentRequest)
            throws SurgeryNotFoundException, DentistNotFoundException, PatientNotFoundException {
        var addedAppointment =  appointmentService.requestAppointment(appointmentRequest);
        var response = new ResponseDto(true,
                addedAppointment,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/approve/{id}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> approveAppointment(@PathVariable Integer id) throws AppointmentNotFoundException {
        var approvedAppointment = appointmentService.changeAppointmentStatus(id, AppointmentStatus.APPROVED.toString());
        var response = new ResponseDto(
                true,
                approvedAppointment,
                HttpStatus.OK.value(),
                null,
                null
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/cancel/{id}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> cancelAppointment(@PathVariable Integer id) throws AppointmentNotFoundException {
        var approvedAppointment = appointmentService.changeAppointmentStatus(id, AppointmentStatus.CANCELLED.toString());
        var response = new ResponseDto(
                true,
                approvedAppointment,
                HttpStatus.OK.value(),
                null,
                null
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> updateAppointment(@RequestBody AppointmentRequest appointmentRequest, @PathVariable Integer id)
            throws AppointmentNotFoundException, SurgeryNotFoundException, DentistNotFoundException, PatientNotFoundException {
        var updatedAppointment = appointmentService.updateAppointment(id, appointmentRequest);
        var response = new ResponseDto(
                true,
                updatedAppointment,
                HttpStatus.OK.value(),
                null,
                null
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
