package pl.bmstefanski.garbanzo.entity;

public interface UserEntity extends Entity<Long> {

  String getName();

  void setName(String name);

  void setIdentifier(Long identifier);

}
