package pl.bmstefanski.garbanzo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class WhitelabelControllerTest {

  private MockMvc mockMvc;

  @Test
  public void errorPageTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/error")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isBadRequest());
  }


}
