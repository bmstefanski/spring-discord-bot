package pl.bmstefanski.garbanzo.controller;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.bmstefanski.garbanzo.dao.impl.UserEntityDaoImpl;
import pl.bmstefanski.garbanzo.entity.impl.UserEntityImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class UserControllerTest {

  @InjectMocks
  private UserController userController;

  @Mock
  private UserEntityDaoImpl userEntityDao;

  private MockMvc mockMvc;

  @Before
  public void initialize() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
  }

  @Test
  public void userGetTest() throws Exception {
    Date currentDate = Date.from(Instant.now());

    UserEntityImpl userEntity = new UserEntityImpl.Builder()
        .withIdentifier(1L)
        .withName("name")
        .setRegistrationDate(currentDate)
        .build();

    when(this.userEntityDao.read(userEntity.getIdentifier())).thenReturn(userEntity);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{id}", 1L)
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.identifier", Matchers.is(1)))
        .andExpect(jsonPath("$.name", Matchers.is("name")))
        .andExpect(jsonPath("$.registrationDate", Matchers.is(currentDate.getTime())));

    verify(this.userEntityDao, times(1)).read(1L);
    verifyNoMoreInteractions(this.userEntityDao);
  }

  @Test
  public void usersGetTest() throws Exception {
    Date currentDate = Date.from(Instant.now());

    UserEntityImpl userEntity = new UserEntityImpl.Builder()
        .withIdentifier(1L)
        .withName("name")
        .setRegistrationDate(currentDate)
        .build();

    List<UserEntityImpl> userEntityList = Collections.singletonList(userEntity);

    when(this.userEntityDao.readAll()).thenReturn(userEntityList);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].identifier", Matchers.is(1)))
        .andExpect(jsonPath("$[0].name", Matchers.is("name")))
        .andExpect(jsonPath("$[0].registrationDate", Matchers.is(currentDate.getTime())));

    verify(this.userEntityDao).readAll();
    verifyNoMoreInteractions(this.userEntityDao);
  }


}
