package cs489.apsd.dentalsurgeryapp.repository;

import cs489.apsd.dentalsurgeryapp.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
