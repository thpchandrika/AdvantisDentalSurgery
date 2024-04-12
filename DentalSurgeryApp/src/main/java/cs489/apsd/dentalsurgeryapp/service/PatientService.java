package cs489.apsd.dentalsurgeryapp.service;


import cs489.apsd.dentalsurgeryapp.domain.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> savePatientList(List<Patient> patients);

    Patient savePatient(Patient patient);
}
