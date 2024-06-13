package ua.storozhukk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GoogleCloudCicdExampleApplicationTests {

    @Autowired
    private MockMvc mvc;


    @Test
    void shouldReturnGreetingsIfNameIsPresent() throws Exception {
        mvc.perform(get("/api/hello").param("name", "Vadym"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("Hello, Vadym, how it's going bro ༼ つ ◕_◕ ༽つ?"));
    }

    @Test
    void shouldReturn400IfNameIsEmpty() throws Exception {
        mvc.perform(get("/api/hello").param("name", ""))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("How i can say hello if i don't know your name?"));
    }

    @Test
    void shouldReturn400IfNameIsNull() throws Exception {
        mvc.perform(get("/api/hello"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}

