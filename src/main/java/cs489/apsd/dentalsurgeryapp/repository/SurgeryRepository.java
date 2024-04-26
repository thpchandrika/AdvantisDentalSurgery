package cs489.apsd.dentalsurgeryapp.repository;

import cs489.apsd.dentalsurgeryapp.domain.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Integer> {
}
