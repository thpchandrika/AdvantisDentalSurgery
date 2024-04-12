package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.Patient;
import cs489.apsd.dentalsurgeryapp.repository.PatientRepository;
import cs489.apsd.dentalsurgeryapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Override
    public List<Patient> savePatientList(List<Patient> patients) {
        return patientRepository.saveAll(patients);
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
