package pl.bmstefanski.garbanzo.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

  public UserEntityImpl() {
  }

  private UserEntityImpl(Builder builder) {
    this.name = builder.name;
    this.identifier = builder.identifier;
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

  public static class Builder implements Buildable<UserEntityImpl> {

    private String name;
    private Long identifier;

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withIdentifier(Long identifier) {
      this.identifier = identifier;
      return this;
    }

    @Override
    public UserEntityImpl build() {
      return new UserEntityImpl(this);
    }

  }

}
