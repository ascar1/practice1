package ru.bellintegrator.practice.office.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;

@Repository
public class OfficeDaoImpl implements OfficeDao {
  private final EntityManager em;
  private final OrganizationDao organizationDao;

  public OfficeDaoImpl(EntityManager em, OrganizationDao organizationDao) {
    this.em = em;
    this.organizationDao = organizationDao;
  }

  @Override
  public List<Office> all() {
    TypedQuery<Office> query = em.createQuery("SELECT p FROM Office p", Office.class);
    return query.getResultList();
  }

  @Override
  public List<Office> getFilter(Long orgId, String name, Boolean isActive) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
    Root<Office> officeRoot = criteriaQuery.from(Office.class);

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(
            builder.equal(officeRoot.get("org"), organizationDao.loadById(orgId))

    );
    if (StringUtils.isNoneEmpty(name)) {
      predicates.add(
              builder.equal(officeRoot.get("name"), name)
      );
    }
    criteriaQuery.select(officeRoot)
            .where(predicates.toArray(new Predicate[]{}));
    return em.createQuery(criteriaQuery).getResultList();
  }

  @Override
  public Office loadById(Long id) {
    return em.find(Office.class, id);
  }

  @Override
  public void save(Office office) {
    em.persist(office);
  }

  @Override
  public void update(OfficeView officeView) {
    Office organization = em.find(Office.class, officeView.id);
    organization.setUpdVal(officeView);

  }

}
