package cs489.apsd.dentalsurgeryapp.service;

import cs489.apsd.dentalsurgeryapp.domain.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurgeryService{
    List<Surgery> addSurgeryList(List<Surgery> surgeries);

    Surgery addSurgery(Surgery surgery);
}
