package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.Dentist;
import cs489.apsd.dentalsurgeryapp.repository.DentistRepository;
import cs489.apsd.dentalsurgeryapp.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {

    @Autowired
    private DentistRepository dentistRepository;
    @Override
    public List<Dentist> addDentistList(List<Dentist> dentists) {
        return dentistRepository.saveAll(dentists);
    }

    @Override
    public Dentist addDentist(Dentist dentist) {
        return null;
    }
}
