/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.Product;
import entity.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author juanluis
 */
public class ProductModel {

    UserTransaction utx;
    EntityManager em;

    public ProductModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }
    
    public List<Product> retrieveAll() {
        Query q = em.createQuery("select o from Product as o");
        return q.getResultList();
    }
    
    public List<Product> retrieveByCategory(int categoryId) {
        Category cat = em.find(Category.class, categoryId);
        Query q = em.createQuery("SELECT o FROM Product o WHERE o.category = :cat");
        q.setParameter("cat", cat);
        
        return q.getResultList();
    }

}
