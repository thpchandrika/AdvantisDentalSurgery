package cs489.apsd.dentalsurgeryapp.controller;
import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
public class AddressControllerTests {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testGetAddressList() throws Exception {
        String url = "http://localhost:8080/adsweb/api/v1/addresses/";
        ResponseEntity<ResponseDto> response = restTemplate.getForEntity(url, ResponseDto.class);
        assertEquals(200, response.getStatusCode().value());
        ResponseDto responseBody = response.getBody();
        assert responseBody != null;
        assertTrue(responseBody.isSuccess());
    }
}
