package cs489.apsd.dentalsurgeryapp.service;


import cs489.apsd.dentalsurgeryapp.domain.Patient;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientRequest;
import cs489.apsd.dentalsurgeryapp.dto.patient.PatientResponse;
import cs489.apsd.dentalsurgeryapp.exceptions.PatientNotFoundException;

import java.util.List;

public interface PatientService {

    List<Patient> savePatientList(List<Patient> patients);
    List<PatientResponse> getAllPatients();
    List<PatientResponse> searchPatient(String searchString);
    PatientResponse getPatientById(Integer id) throws PatientNotFoundException;

    Patient savePatient(PatientRequest request);
    void deletePatient(Integer id) throws PatientNotFoundException;
    Patient updatePatient(Integer id,PatientRequest patient) throws PatientNotFoundException;
}
