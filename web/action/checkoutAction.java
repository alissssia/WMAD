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
import web.ViewManager;

/**
 *
 * @author entel
 */
public class checkoutAction implements Action {

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        session.setAttribute("paypalCheckoutStarted", true);

        req.setAttribute("cart", cart);
        req.setAttribute("showPaypal", true);

        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}
