package cs489.apsd.dentalsurgeryapp.controller;
import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientRequest;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientResponse;
import cs489.apsd.dentalsurgeryapp.exceptions.PatientNotFoundException;
import cs489.apsd.dentalsurgeryapp.service.AddressService;
import cs489.apsd.dentalsurgeryapp.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private AddressService addressService;


    @GetMapping(value = {"", "/"})
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
