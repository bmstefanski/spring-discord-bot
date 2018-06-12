package pl.bmstefanski.garbanzo.entity;

import java.util.Date;

public interface UserEntity extends Entity<Long> {

  String getName();

  void setName(String name);

  Date getRegistrationDate();

  void setRegistrationDate(Date registrationDate);

}
