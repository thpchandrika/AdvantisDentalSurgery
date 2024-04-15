package cs489.apsd.dentalsurgeryapp.service.impl;

import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.repository.AddressRepository;
import cs489.apsd.dentalsurgeryapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> addAddressList(List<Address> addresses) {
      return addressRepository.saveAll(addresses);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }
}