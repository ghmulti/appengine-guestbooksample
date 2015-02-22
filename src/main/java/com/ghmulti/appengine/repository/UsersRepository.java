package com.ghmulti.appengine.repository;

import com.ghmulti.appengine.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
@Repository("usersRepository")
public class UsersRepository {

    @PersistenceUnit(unitName = "caf")
    private EntityManager entityManager;

    public UserProfile findByEmail(String email) {
        try {
            TypedQuery<UserProfile> query = entityManager.createQuery("select up from UserProfile up where up.email = :email", UserProfile.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
