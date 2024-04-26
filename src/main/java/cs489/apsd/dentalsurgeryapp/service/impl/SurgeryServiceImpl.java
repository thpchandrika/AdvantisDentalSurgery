package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.Surgery;
import cs489.apsd.dentalsurgeryapp.exceptions.SurgeryNotFoundException;
import cs489.apsd.dentalsurgeryapp.repository.SurgeryRepository;
import cs489.apsd.dentalsurgeryapp.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurgeryServiceImpl implements SurgeryService {

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Override
    public List<Surgery> addSurgeryList(List<Surgery> surgeries) {
        return surgeryRepository.saveAll(surgeries);
    }

    @Override
    public Surgery addSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }

    @Override
    public Surgery getSurgeryById(Integer surgeryId) throws SurgeryNotFoundException {
        return surgeryRepository.findById(surgeryId).orElseThrow(() ->
                new SurgeryNotFoundException(
                        String.format("Surgery with surgery id %d is not found", surgeryId)
                ));
    }
}
