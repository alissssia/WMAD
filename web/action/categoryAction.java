/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;

/**
 *
 * @author entel
 */
public class categoryAction implements Action {
    private CategoryModel categoryModel;
    private ProductModel productModel;
    
    public categoryAction(CategoryModel categoryModel, ProductModel productModel) {
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        int categoryId = Integer.parseInt(req.getParameter("categoryid"));
        
        HttpSession session = req.getSession();
        session.setAttribute("lastCategoryId", categoryId);
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("products", productModel.retrieveByCategory(categoryId));
        req.setAttribute("currentCat", categoryId);
        
        ViewManager.nextView(req, resp, "/view/category.jsp");
    }
}
