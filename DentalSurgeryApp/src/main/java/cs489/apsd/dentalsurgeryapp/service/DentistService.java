package cs489.apsd.dentalsurgeryapp.service;

import cs489.apsd.dentalsurgeryapp.domain.Dentist;

import java.util.List;

public interface DentistService {

    List<Dentist> addDentistList(List<Dentist> dentists);



    Dentist addDentist(Dentist dentist);
}
