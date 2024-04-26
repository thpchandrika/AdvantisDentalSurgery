package cs489.apsd.dentalsurgeryapp.repository;

import cs489.apsd.dentalsurgeryapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
