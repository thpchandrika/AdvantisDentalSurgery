package cs489.apsd.dentalsurgeryapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs489.apsd.dentalsurgeryapp.controller.AddressController;
import cs489.apsd.dentalsurgeryapp.controller.DentistController;
import cs489.apsd.dentalsurgeryapp.domain.Address;
import cs489.apsd.dentalsurgeryapp.domain.Dentist;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistAdapter;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistRequest;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistResponse;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DentistController.class)
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
        dentists.add(new Dentist("D001","John", "Doe", "1234567890", "john.doe@mailinator.com", "General Dentistry"));
        dentists.add(new Dentist("D002","Jane", "Smith", "9876543210", "jane.smith@mailinator.com", "Orthodontics"));
        when(dentistService.getAllDentist()).thenReturn(DentistAdapter.getDentistResponseList(dentists));

        mockMvc.perform(get("/ads/dentists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].dentistID").value("D001"))
                .andExpect(jsonPath("$.data[1].specialization").value("Orthodontics"));
    }

    @Test
    public void testGetDentistById() throws Exception {
        Dentist dentist = new Dentist("D001", "John", "Doe", "1234567890", "john.doe@mailinator.com", "General Dentistry");
        when(dentistService.getDentistById(1)).thenReturn(DentistAdapter.getDentistResponse(dentist));

        mockMvc.perform(get("/ads/dentists/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.data.dentistID").value("D001"))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"))
                .andExpect(jsonPath("$.data.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.data.email").value("john.doe@mailinator.com"))
                .andExpect(jsonPath("$.data.specialization").value("General Dentistry"));
    }

    @Test
    public void testRegisterDentist() throws Exception {
        DentistRequest request = new DentistRequest("D100", "John", "Doe",
                "1234567890", "john.doe@mailinator.com", "General Dentistry");

        DentistResponse dentistResponse = new DentistResponse(1,"D001", "John", "Doe", "1234567890", "john.doe@example.com", "General Dentistry");
        when(dentistService.addDentist(any(DentistRequest.class))).thenReturn(dentistResponse);

        mockMvc.perform(post("/ads/dentists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.dentistID").value("D001"))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"))
                .andExpect(jsonPath("$.data.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.data.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.data.specialization").value("General Dentistry"));
    }
}
