package cs489.apsd.dentalsurgeryapp.service;

import cs489.apsd.dentalsurgeryapp.domain.Dentist;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistRequest;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistResponse;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;

import java.util.List;

public interface DentistService {

    List<Dentist> addDentistList(List<Dentist> dentists);
    List<DentistResponse> getAllDentist();

    DentistResponse addDentist(DentistRequest dentistRequest);
    DentistResponse getDentistById(Integer id) throws DentistNotFoundException;
}
