/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.AppUser;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author entel
 */
public class UserModel {

    private UserTransaction utx;
    private EntityManager em;

    public UserModel(EntityManager em, UserTransaction utx) {
        this.em = em;
        this.utx = utx;
    }

    public void create(AppUser user) {
        try {
            utx.begin();
            em.persist(user);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            throw new RuntimeException(e);
        }
    }

    public AppUser findByEmail(String email) {
        try {
            TypedQuery<AppUser> q = em.createNamedQuery("AppUser.findByEmail", AppUser.class);
            q.setParameter("email", email);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public AppUser login(String email, String password) {
        AppUser user = findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
