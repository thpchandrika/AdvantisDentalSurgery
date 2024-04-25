package cs489.apsd.dentalsurgeryapp.repository;

import cs489.apsd.dentalsurgeryapp.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByDentistId(Integer dentistId);
    List<Appointment> findByPatientId(Integer patientId);
}
