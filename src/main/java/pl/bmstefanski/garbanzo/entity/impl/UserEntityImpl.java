package pl.bmstefanski.garbanzo.entity.impl;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import pl.bmstefanski.garbanzo.entity.UserEntity;
import pl.bmstefanski.garbanzo.util.Buildable;

@Entity
@Table(name = "users")
public class UserEntityImpl implements UserEntity {

  @Column(nullable = false)
  private String name;

  @Id
  @Column(nullable = false)
  private Long identifier;

  @Column(name = "registration", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date registrationDate;

  public UserEntityImpl() {
  }

  private UserEntityImpl(Builder builder) {
    this.name = builder.name;
    this.identifier = builder.identifier;
    this.registrationDate = builder.registrationDate;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Long getIdentifier() {
    return this.identifier;
  }

  @Override
  public void setIdentifier(Long identifier) {
    this.identifier = identifier;
  }

  @Override
  public Date getRegistrationDate() {
    return this.registrationDate;
  }

  @Override
  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public static class Builder implements Buildable<UserEntityImpl> {

    private String name;
    private Long identifier;
    private Date registrationDate;

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withIdentifier(Long identifier) {
      this.identifier = identifier;
      return this;
    }

    public Builder setRegistrationDate(Date registrationDate) {
      this.registrationDate = registrationDate;
      return this;
    }

    @Override
    public UserEntityImpl build() {
      return new UserEntityImpl(this);
    }

  }

}
