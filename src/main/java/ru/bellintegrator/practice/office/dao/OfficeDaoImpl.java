package  ru.bellintegrator.practice.office.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {
    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Office> all(){
        TypedQuery<Office> query = em.createQuery("SELECT p FROM Office p", Office.class);
        return query.getResultList();
    }
    @Override
    public List<Office> getFilter(Long orgid , String name, Boolean is_active){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office>  criteriaQuery = builder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(
                builder.equal(officeRoot.get("org_id"), orgid)
        );
        if (name != ""){
            predicates.add(
                    builder.equal(officeRoot.get("name"), name)
            );
        }
        criteriaQuery.select(officeRoot)
                    .where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Office loadByID (long id){
        return em.find(Office.class,id);
    }
    @Override
    public void save (Office office){
        em.persist(office);
    }
    @Override
    public void update (OfficeView officeView){
        Office organization = em.find(Office.class,officeView.id);
        organization.SetUpdVal(officeView);

    }

}
