package cs489.apsd.dentalsurgeryapp.service;

import cs489.apsd.dentalsurgeryapp.domain.Address;

import java.util.List;

public interface AddressService {

    List<Address> addAddressList(List<Address> addresses);
    Address addAddress(Address address);
}
