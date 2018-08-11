package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager em;
    @Autowired
    public UserDaoImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<User> all (){
        TypedQuery<User> query = em.createQuery("SELECT p FROM User p", User.class);
        return query.getResultList();
    }
    @Override
    public User loadByID (Long id){
        return em.find(User.class,id);
    }
    @Override
    public  List<User> GetFilter(Long officeid, String firstname,String second_name, String middle_name,String position, Integer doc_code , String citizenship_code  ){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User>  userRoot = criteriaQuery.from(User.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(
                builder.equal(userRoot.get("office_id"),officeid));
        if (firstname != ""){
            predicates.add(
                    builder.equal(userRoot.get("first_name"),firstname)
            );
        }
        if (second_name != ""){
            predicates.add(
                    builder.equal(userRoot.get("second_name"),second_name)
            );
        }
        if (middle_name != ""){
            predicates.add(
                    builder.equal(userRoot.get("middle_name"),middle_name)
            );
        }
        if (position != ""){
            predicates.add(
                    builder.equal(userRoot.get("position"),position)
            );
        }
        if (doc_code != 0){
            predicates.add(
                    builder.equal(userRoot.get("doc_Code"),doc_code)
            );
        }
        if (citizenship_code != ""){
            predicates.add(
                    builder.equal(userRoot.get("citizenship_code"),citizenship_code)
            );
        }
        criteriaQuery.select(userRoot)
                .where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void save (User user){
        em.persist(user);
    }
    @Override
    public void update(User user){
        em.merge(user);
    }


}
