package bzh.ineed.rolandgarros;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void getStatus() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.version", is("1.0")))
                .andExpect(jsonPath("$.status", is("Welcome everyone to the Roland Garros website!")));
    }

    @Test
    @Order(2)
    public void getStatusWithName() throws Exception {
        mockMvc.perform(get("/api/status?name=Martin"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.version", is("1.0")))
                .andExpect(jsonPath("$.status", is("Welcome Martin to the Roland Garros website!")));
    }

}
