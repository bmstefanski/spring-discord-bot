package pl.bmstefanski.garbanzo.dao.impl;

import org.springframework.stereotype.Repository;
import pl.bmstefanski.garbanzo.dao.AbstractEntityDao;
import pl.bmstefanski.garbanzo.entity.impl.UserEntityImpl;

@Repository
public class UserEntityDaoImpl extends AbstractEntityDao<UserEntityImpl, Long> {

  public UserEntityDaoImpl() {
    super(UserEntityImpl.class);
  }

}
