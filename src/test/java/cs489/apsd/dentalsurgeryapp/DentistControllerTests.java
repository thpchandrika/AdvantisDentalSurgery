package cs489.apsd.dentalsurgeryapp;

import cs489.apsd.dentalsurgeryapp.controller.AddressController;
import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.domain.Dentist;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistAdapter;
import cs489.apsd.dentalsurgeryapp.service.AddressService;
import cs489.apsd.dentalsurgeryapp.service.DentistService;
import cs489.apsd.dentalsurgeryapp.service.impl.AdsUserDetailsService;
import cs489.apsd.dentalsurgeryapp.util.JwtUtilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class DentistControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DentistService dentistService;

    @MockBean
    private AddressService addressService;

    @MockBean
    private JwtUtilityService jwtUtilityService;

    @MockBean
    private AdsUserDetailsService adsUserDetailsService;

    @Test
    public void testGetDentistList() throws Exception {
        List<Dentist> dentists = new ArrayList<>();
        dentists.add(new Dentist("D001","John", "Doe", "1234567890", "john.doe@example.com", "General Dentistry"));
        dentists.add(new Dentist("D002","Jane", "Smith", "9876543210", "jane.smith@example.com", "Orthodontics"));
        when(dentistService.getAllDentist()).thenReturn(DentistAdapter.getDentistResponseList(dentists));

        mockMvc.perform(get("/adsweb/api/v1/dentists/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].dentistID").value("D001"))
                .andExpect(jsonPath("$.data[1].specialization").value("Orthodontics"));
    }

}
