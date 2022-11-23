package bzh.ineed.rolandgarros.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllCountries() throws Exception {
        mockMvc.perform(get("/api/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(255)))
                .andExpect(jsonPath("$[0].name", is("Afghanistan")))
                .andExpect(jsonPath("$[0].alpha2Code", is("AF")))
                .andExpect(jsonPath("$[0].alpha3Code", is("AFG")))
                .andExpect(jsonPath("$[198].name", is("Sao Tome and Principe")))
                .andExpect(jsonPath("$[198].alpha2Code", is("ST")))
                .andExpect(jsonPath("$[198].alpha3Code", is("STP")));
    }
}
