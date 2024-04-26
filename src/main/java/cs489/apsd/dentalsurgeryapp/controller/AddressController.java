package cs489.apsd.dentalsurgeryapp.controller;

import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientResponse;
import cs489.apsd.dentalsurgeryapp.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Address", description = "an address api")
@RestController
@RequestMapping("/adsweb/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public ResponseEntity<ResponseDto> getAddressList(){
        var addresses =  addressService.getAllAddress();
        var response = new ResponseDto(true,
                addresses,
                HttpStatus.OK.value(),
                null,
                null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
