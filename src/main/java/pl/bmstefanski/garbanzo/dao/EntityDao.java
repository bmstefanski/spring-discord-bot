package pl.bmstefanski.garbanzo.dao;

import java.util.List;
import pl.bmstefanski.garbanzo.entity.Entity;

public interface EntityDao<T extends Entity, ID> {

  T create(T entity);

  T read(ID identifier);

  List<T> readAll();

  void update(T entity);

  void delete(T entity);

  void deleteByIdentifier(ID identifier);

}
