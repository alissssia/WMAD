/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;
import cart.ShoppingCart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductModel;
import entity.Product;
import web.ViewManager;

/**
 *
 * @author entel
 */
public class updatecartAction implements Action {
    private ProductModel productModel;

    public updatecartAction(ProductModel productModel) {
        this.productModel = productModel;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
        } else {
            try {
                int productId = Integer.parseInt(req.getParameter("productid"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                
                Product product = productModel.find(productId);

                if (quantity <= 0) {
                    cart.remove(productId);
                } else if (quantity <= product.getStock()) {
                    cart.update(productId, quantity);
                } else {
                    cart.update(productId, product.getStock());
                }

            } catch (NumberFormatException e) {
                // si meten algo que no es número, no hacemos nada
            }
        }

        session.setAttribute("cart", cart);
        req.setAttribute("cart", cart);

        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}
