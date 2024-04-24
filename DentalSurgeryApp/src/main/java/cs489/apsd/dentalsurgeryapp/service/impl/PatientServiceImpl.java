package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.domain.Patient;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientRequest;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientResponse;
import cs489.apsd.dentalsurgeryapp.exceptions.PatientNotFoundException;
import cs489.apsd.dentalsurgeryapp.repository.AddressRepository;
import cs489.apsd.dentalsurgeryapp.repository.PatientRepository;
import cs489.apsd.dentalsurgeryapp.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public List<Patient> savePatientList(List<Patient> patients) {
        return patientRepository.saveAll(patients);
    }

    @Override
    public List<PatientResponse> getAllPatients() {
       patientRepository.findAll().stream().forEach(p -> System.out.println(p.getMailingAddress().getId()));
        var list = patientRepository.findAll(Sort.by("lastName"))
                .stream()
                .map(p-> new PatientResponse(
                        p.getId(),
                        p.getPatientNumber(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getPhoneNumber(),
                        p.getEmail(),
                        p.getDob(),
                        (p.getMailingAddress() != null)?new Address(
                                p.getMailingAddress().getId(),
                                p.getMailingAddress().getStreet(),
                                p.getMailingAddress().getZip(),
                                p.getMailingAddress().getCity(),
                                p.getMailingAddress().getState()
                        ): null
                )).toList();
        return list;
    }

    @Override
    public List<PatientResponse> searchPatient(String searchString) {
        var list = patientRepository.findAll()
                .stream()
                .filter(p-> p.getPatientNumber().contains(searchString) ||
                        p.getFirstName().contains(searchString) ||
                        p.getLastName().contains(searchString)||
                        p.getPhoneNumber().contains(searchString))
                .map(p-> new PatientResponse(
                        p.getId(),
                        p.getPatientNumber(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getPhoneNumber(),
                        p.getEmail(),
                        p.getDob(),
                        (p.getMailingAddress() != null)?new Address(
                                p.getMailingAddress().getId(),
                                p.getMailingAddress().getStreet(),
                                p.getMailingAddress().getZip(),
                                p.getMailingAddress().getCity(),
                                p.getMailingAddress().getState()
                        ): null
                )).toList();
//        var list = patientRepository.findByPatientNumberOrFirstNameOrLastNameOrPhoneNumberOrEmail(searchString)
//                .stream()
//                .map(p-> new PatientResponse(
//                        p.getId(),
//                        p.getPatientNumber(),
//                        p.getFirstName(),
//                        p.getLastName(),
//                        p.getPhoneNumber(),
//                        p.getEmail(),
//                        p.getDob(),
//                        (p.getMailingAddress() != null)?new Address(
//                                p.getMailingAddress().getId(),
//                                p.getMailingAddress().getStreet(),
//                                p.getMailingAddress().getZip(),
//                                p.getMailingAddress().getCity(),
//                                p.getMailingAddress().getState()
//                        ): null
//                )).toList();
        return list;
    }

    @Override
    public PatientResponse getPatientById(Integer id) throws PatientNotFoundException{
        var patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(String
                        .format("Patient with id %d not found", id)));
        return new PatientResponse(
                patient.getId(),
                patient.getPatientNumber(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getDob(),
                (patient.getMailingAddress() != null)?new Address(
                        patient.getMailingAddress().getId(),
                        patient.getMailingAddress().getStreet(),
                        patient.getMailingAddress().getZip(),
                        patient.getMailingAddress().getCity(),
                        patient.getMailingAddress().getState()
                ): null);
    }

    @Override
    public Patient savePatient(PatientRequest request) {
        var patient = new Patient(request.patientNumber(),
                request.firstName(),
                request.lastName(),
                request.phoneNumber(),
                request.email(),
                request.dob(),
                new Address(null,request.mailingAddress().getStreet(),
                        request.mailingAddress().getZip(),
                        request.mailingAddress().getCity(),
                        request.mailingAddress().getState())
                );
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Integer id) throws PatientNotFoundException {
        var existingPatient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException(
                        String.format("Patient with id %d is not found", id)));
        patientRepository.delete(existingPatient);
    }

    @Override
    @Transactional
    public Patient updatePatient(Integer id,PatientRequest request) throws PatientNotFoundException {
        var existingPatient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException(
                        String.format("Patient with id %d is not found", id)));
        existingPatient.setPatientNumber(request.patientNumber());
        existingPatient.setFirstName(request.firstName());
        existingPatient.setLastName(request.lastName());
        existingPatient.setPhoneNumber(request.phoneNumber());
        existingPatient.setEmail(request.email());
        existingPatient.setDob(request.dob());
        System.out.println(existingPatient.getMailingAddress().getCity());
        if(existingPatient.getMailingAddress()!=null) {
           // var address = existingPatient.getMailingAddress();
            existingPatient.getMailingAddress().setStreet(request.mailingAddress().getStreet());
            existingPatient.getMailingAddress().setCity(request.mailingAddress().getCity());
            existingPatient.getMailingAddress().setState(request.mailingAddress().getState());
            existingPatient.getMailingAddress().setZip(request.mailingAddress().getZip());
        } else {
            var newAddress = new Address();
            newAddress.setStreet(request.mailingAddress().getStreet());
            newAddress.setCity(request.mailingAddress().getCity());
            newAddress.setState(request.mailingAddress().getState());
            newAddress.setZip(request.mailingAddress().getZip());
            existingPatient.setMailingAddress(newAddress);
        }
       return patientRepository.save(existingPatient);
    }
}
