package ru.bellintegrator.practice.countries.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.countries.model.Countries;

@Repository
public class CountriesDaoImpl implements CountriesDao {
  private final EntityManager em;

  @Autowired
  public CountriesDaoImpl(EntityManager em) {
    this.em = em;
  }

  @Override
  public List<Countries> all() {
    TypedQuery<Countries> query = em.createQuery("SELECT h FROM Countries h", Countries.class);
    return query.getResultList();
  }

  @Override
  public Countries loadById(Integer id) {
    return em.find(Countries.class, id);
  }

  @Override
  public void save(Countries countries) {
    em.persist(countries);
  }
}
