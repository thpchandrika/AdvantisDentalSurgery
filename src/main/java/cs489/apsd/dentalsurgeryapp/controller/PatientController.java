package cs489.apsd.dentalsurgeryapp.controller;
import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientRequest;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientResponse;
import cs489.apsd.dentalsurgeryapp.exceptions.PatientNotFoundException;
import cs489.apsd.dentalsurgeryapp.service.AddressService;
import cs489.apsd.dentalsurgeryapp.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Patient", description = "a patient api")
@RestController
@RequestMapping("/adsweb/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private AddressService addressService;


    @GetMapping(value = {"", "/"})
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<List<PatientResponse>> getPatientList(){
        var patients =  patientService.getAllPatients();
        var response = new ResponseDto(true,
                patients,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/search/{searchString}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<List<PatientResponse>> searchPatient(){
        var patients =  patientService.getAllPatients();
        var response = new ResponseDto(true,
                patients,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<?> getPatient(@PathVariable Integer id) throws PatientNotFoundException {
        var patient =  patientService.getPatientById(id);
        var response = new ResponseDto(true,
                patient,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<?> addPatient(@RequestBody @Valid PatientRequest request){
        var newPatient = patientService.savePatient(request);
        var response = new ResponseDto(true,
                newPatient,
                HttpStatus.CREATED.value(),
                null,
                null);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> updatePatient(@PathVariable Integer id,
                                           @RequestBody PatientRequest request) throws PatientNotFoundException {
        var updatedPatient = patientService.updatePatient(id,request);
        var response = new ResponseDto(true,
                updatedPatient,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public  ResponseEntity<ResponseDto> deletePatient(@PathVariable Integer id) throws PatientNotFoundException {
        patientService.deletePatient(id);
        var response = new ResponseDto(true,
                null,
                HttpStatus.NO_CONTENT.value(),
                null,
                null);
        return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
    }
}
