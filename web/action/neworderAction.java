/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import javax.servlet.http.*;
import model.ProductModel;
import entity.Product;
import model.CategoryModel;
import cart.ShoppingCart;
import web.ViewManager;

/**
 *
 * @author entel
 */
public class neworderAction implements Action {
    private CategoryModel categoryModel;
    private ProductModel productModel;
    
    public neworderAction(CategoryModel categoryModel, ProductModel productModel) {
        this.productModel = productModel;
        this.categoryModel = categoryModel;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int categoryId = Integer.parseInt(req.getParameter("categoryid"));
        
        Product product = productModel.find(productId);
        
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            //session.setAttribute("cart", cart);
        }
        
        int amountInCart = cart.getAmountOfProduct(productId);

        if (amountInCart < product.getStock()) {
            cart.add(product);
        }

        session.setAttribute("cart", cart);
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("products", productModel.retrieveByCategory(categoryId));
        req.setAttribute("currentCat", categoryId);
        
        
        ViewManager.nextView(req, resp, "/view/category.jsp");
        
    }
    
}
