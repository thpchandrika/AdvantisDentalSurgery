package cs489.apsd.dentalsurgeryapp.repository;

import cs489.apsd.dentalsurgeryapp.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
