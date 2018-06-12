package pl.bmstefanski.garbanzo.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.bmstefanski.garbanzo.dao.impl.UserEntityDaoImpl;
import pl.bmstefanski.garbanzo.entity.UserEntity;
import pl.bmstefanski.garbanzo.entity.impl.UserEntityImpl;

@RequestMapping("/api/v1/")
@RestController
public class UserController {

  private final UserEntityDaoImpl userEntityDao;

  public UserController(UserEntityDaoImpl userEntityDao) {
    this.userEntityDao = userEntityDao;
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/user/{id}")
  @ResponseBody
  public UserEntity getUserEntity(@PathVariable("id") Long id) {
    return this.userEntityDao.read(id);
  }

  @RequestMapping("/users")
  @ResponseBody
  public List<UserEntityImpl> getAllUserEntities() {
    return this.userEntityDao.readAll();
  }

}
