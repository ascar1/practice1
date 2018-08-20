package ru.bellintegrator.practice.user.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.countries.dao.CountriesDao;
import ru.bellintegrator.practice.docs.dao.DocsDao;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

@Repository
public class UserDaoImpl implements UserDao {
  private static Logger log = LoggerFactory.getLogger(OrganizationDao.class.getName());
  private final EntityManager em;
  @Autowired
  private OfficeDao officeDao;
  @Autowired
  private DocsDao docsDao;
  @Autowired
  private CountriesDao countriesDao;

  @Autowired
  public UserDaoImpl(EntityManager em) {
    this.em = em;
  }

  @Override
  public List<User> all() {
    TypedQuery<User> query = em.createQuery("SELECT p FROM user p", User.class);
    return query.getResultList();
  }

  @Override
  public User loadById(Long id) {
    return em.find(User.class, id);
  }

  @Override
  public List<User> getFilter(Long officeid,
                              String firstname,
                              String secondName,
                              String middleName,
                              String position,
                              Long docCode,
                              Integer citizenshipCode) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
    Root<User> userRoot = criteriaQuery.from(User.class);

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(
            builder.equal(userRoot.get("office"), officeDao.loadById(officeid)));

    if (StringUtils.isNoneEmpty(firstname)) {
      predicates.add(
              builder.equal(userRoot.get("first_name"), firstname)
      );
    }
    if (StringUtils.isNoneEmpty(secondName)) {
      predicates.add(
              builder.equal(userRoot.get("second_name"), secondName)
      );
    }
    if (StringUtils.isNoneEmpty(middleName)) {
      predicates.add(
              builder.equal(userRoot.get("middle_name"), middleName)
      );
    }
    if (StringUtils.isNoneEmpty(position)) {
      predicates.add(
              builder.equal(userRoot.get("position"), position)
      );
    }
    if (docCode != null) {
      predicates.add(
              builder.equal(userRoot.get("docs"), docsDao.loadById(docCode))
      );
    }
    if (citizenshipCode != null) {
      predicates.add(
              builder.equal(userRoot.get("countries"), countriesDao.loadById(citizenshipCode))
      );
    }
    criteriaQuery.select(userRoot)
            .where(predicates.toArray(new Predicate[]{}));
    return em.createQuery(criteriaQuery).getResultList();
  }

  @Override
  public void save(User user) {
    em.persist(user);
  }

  @Override
  public void update(UserView userView) {
    User user = em.find(User.class, userView.id);
    user.setUpdVal(userView);
  }


}
