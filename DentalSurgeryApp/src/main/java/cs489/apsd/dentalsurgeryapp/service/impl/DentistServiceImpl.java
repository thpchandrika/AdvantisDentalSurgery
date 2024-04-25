package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.constant.RoleType;
import cs489.apsd.dentalsurgeryapp.domain.Dentist;
import cs489.apsd.dentalsurgeryapp.domain.Role;
import cs489.apsd.dentalsurgeryapp.domain.User;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistAdapter;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistRequest;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistResponse;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;
import cs489.apsd.dentalsurgeryapp.repository.DentistRepository;
import cs489.apsd.dentalsurgeryapp.repository.UserRepository;
import cs489.apsd.dentalsurgeryapp.service.DentistService;
import cs489.apsd.dentalsurgeryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Dentist> addDentistList(List<Dentist> dentists) {
        return dentistRepository.saveAll(dentists);
    }

    @Override
    public List<DentistResponse> getAllDentist() {
        var dentistList = dentistRepository.findAll();
        return DentistAdapter.getDentistResponseList(dentistList);
    }

    @Override
    public DentistResponse addDentist(DentistRequest dentistRequest) {
        var dentist = DentistAdapter.getDentist(dentistRequest);
        var savedDentist = dentistRepository.save(dentist);
        //creating dentist user
        String username = dentistRequest.email();
        String password = dentistRequest.dentistID();
        List<String> dentistRole = List.of(RoleType.DENTIST.toString());
        userService.createUserWithRole(username, password, dentistRole);
        return DentistAdapter.getDentistResponse(savedDentist);
    }

    @Override
    public DentistResponse getDentistById(Integer id) throws DentistNotFoundException{
        var dentist =  dentistRepository.findById(id)
                .orElseThrow(() -> new DentistNotFoundException(String.format("Dentist with id %d not found", id)));
        return DentistAdapter.getDentistResponse(dentist);
    }


}
