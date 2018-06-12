package pl.bmstefanski.garbanzo.entity;

public interface Entity<T> {

  T getIdentifier();

  void setIdentifier(T identifier);

}
