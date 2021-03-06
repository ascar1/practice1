package ru.bellintegrator.practice.organization.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
  private final EntityManager em;
  private static Logger log = LoggerFactory.getLogger(OrganizationDao.class.getName());

  @Autowired
  public OrganizationDaoImpl(EntityManager em) {
    this.em = em;
  }

  @Override
  public List<Organization> all() {
    TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p",
            Organization.class);
    return query.getResultList();
  }

  @Override
  public Organization loadById(Long id) {
    return em.find(Organization.class, id);
  }

  @Override
  public Organization loadByName(String name) {
    CriteriaQuery<Organization> criteriaQuery = organizationCriteriaQuery(name);
    TypedQuery<Organization> query = em.createQuery(criteriaQuery);
    return query.getSingleResult();
  }

  @Override
  public List<Organization> filter(String name, String inn, Boolean isactive) {
    return filterQuery(name, inn, isactive);
  }

  @Override
  public void save(Organization organization) {
    em.persist(organization);
  }

  @Override
  public void update(OrganizationView organizationView) {
    Organization organization = em.find(Organization.class, organizationView.id);
    organization.setUpdVal(organizationView);
  }

  private CriteriaQuery<Organization> organizationCriteriaQuery(String name) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);

    Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
    criteriaQuery.where(builder.equal(organizationRoot.get("name"), name));
    return criteriaQuery;
  }

  private CriteriaQuery<Organization> filterId(Long id) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
    Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
    criteriaQuery.where(builder.equal(organizationRoot.get("id"), id));
    return criteriaQuery;
  }

  private List<Organization> filterQuery(String name, String inn, Boolean isactive) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
    Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(
            builder.equal(organizationRoot.get("name"), name));
    if (StringUtils.isNoneEmpty(inn)) {
      predicates.add(
              builder.equal(organizationRoot.get("inn"), inn));
    }
    if (isactive != null) {
      predicates.add(
              builder.equal(organizationRoot.get("is_active"), isactive));
    }
    criteriaQuery.select(organizationRoot)
            .where(predicates.toArray(new Predicate[]{}));
    return em.createQuery(criteriaQuery).getResultList();
  }

}
