package pl.bmstefanski.garbanzo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class WhitelabelControllerTest {

  @Mock
  private WhitelabelController whitelabelController;

  private MockMvc mockMvc;

  @Before
  public void initialize() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.whitelabelController).build();
  }

  @Test
  public void errorPageTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/error")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isBadRequest());
  }


}
