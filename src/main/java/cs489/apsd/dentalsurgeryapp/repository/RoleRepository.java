package cs489.apsd.dentalsurgeryapp.repository;


import cs489.apsd.dentalsurgeryapp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
