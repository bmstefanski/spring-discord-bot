package pl.bmstefanski.garbanzo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import pl.bmstefanski.garbanzo.entity.Entity;

public abstract class AbstractEntityDao<T extends Entity, ID> implements EntityDao<T, ID> {

  @PersistenceContext
  private EntityManager entityManager;

  private final Class<T> clazz;

  protected AbstractEntityDao(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Transactional
  @Override
  public T create(T entity) {
    return this.entityManager.merge(entity);
  }

  @Transactional
  @Override
  public T read(ID identifier) {
    return this.entityManager.find(clazz, identifier);
  }

  @Transactional
  @Override
  public void update(T entity) {
    this.entityManager.persist(entity);
  }

  @Transactional
  @Override
  public void delete(T entity) {
    this.entityManager.remove(entity);
  }

  @Transactional
  @Override
  public List<T> readAll() {
    return this.entityManager.createQuery("FROM " + clazz.getName()).getResultList();
  }

  @Transactional
  @Override
  public void deleteByIdentifier(ID identifier) {
    T entity = this.read(identifier);
    this.entityManager.remove(entity);
  }

}
