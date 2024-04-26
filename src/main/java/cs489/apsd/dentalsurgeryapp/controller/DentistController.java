package cs489.apsd.dentalsurgeryapp.controller;

import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistRequest;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;
import cs489.apsd.dentalsurgeryapp.service.DentistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Dentist", description = "a dentist api")
@RestController
@RequestMapping("/adsweb/api/v1/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping()
    public ResponseEntity<ResponseDto> getAllDentist(){
        var dentistResponse = dentistService.getAllDentist();
        var response = new ResponseDto(true, dentistResponse, HttpStatus.OK.value(), null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getAllDentist(@PathVariable Integer id) throws DentistNotFoundException {
        var dentistResponse = dentistService.getDentistById(id);
        var response = new ResponseDto(true, dentistResponse, HttpStatus.OK.value(), null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> registerDentist(@RequestBody DentistRequest request){
        var dentistResponse = dentistService.addDentist(request);
        var response = new ResponseDto(true, dentistResponse, HttpStatus.OK.value(), null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
