package cs489.apsd.dentalsurgeryapp;

import cs489.apsd.dentalsurgeryapp.controller.AddressController;

import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.domain.User;
import cs489.apsd.dentalsurgeryapp.service.AddressService;

import cs489.apsd.dentalsurgeryapp.service.impl.AdsUserDetailsService;
import cs489.apsd.dentalsurgeryapp.util.JwtUtilityService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AddressController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AddressControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @MockBean
    private JwtUtilityService jwtUtilityService;

    @MockBean
    private AdsUserDetailsService adsUserDetailsService;

    @Test
    public void testGetAddressList() throws Exception {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1,"123 Street", "45655", "Fairfield", "IA"));
        addresses.add(new Address(2,"456 Avenue", "65445", "Euless", "TX"));
        when(addressService.getAllAddress()).thenReturn(addresses);
        mockMvc.perform(get("/adsweb/api/v1/addresses/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].street").value("123 Street"))
                .andExpect(jsonPath("$.data[1].city").value("Euless"));
    }

}
