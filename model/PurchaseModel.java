/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.AppUser;
import entity.PurchaseItem;
import entity.PurchaseOrder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author entel
 */
public class PurchaseModel {

    private UserTransaction utx;
    private EntityManager em;

    public PurchaseModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }

    public void createOrder(PurchaseOrder order) {
        try {
            utx.begin();
            em.persist(order);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            throw new RuntimeException(e);
        }
    }

    public void createItem(PurchaseItem item) {
        try {
            utx.begin();
            em.persist(item);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            throw new RuntimeException(e);
        }
    }

    public List<PurchaseOrder> findOrdersByUser(AppUser user) {
        Query q = em.createQuery("SELECT o FROM PurchaseOrder o WHERE o.user = :user ORDER BY o.purchaseDate DESC");
        q.setParameter("user", user);
        return q.getResultList();
    }

    public List<PurchaseItem> findItemsByOrder(PurchaseOrder order) {
        Query q = em.createQuery("SELECT i FROM PurchaseItem i WHERE i.order = :order");
        q.setParameter("order", order);
        return q.getResultList();
    }
}
