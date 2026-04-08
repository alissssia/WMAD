/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

/**
 *
 * @author entel
 */

import cart.ShoppingCart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.ViewManager;


public class viewcartAction implements Action {

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();

        // Obtener el carrito de la sesión
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        // Si no existe, crear uno vacío
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        // Pasarlo al request para la JSP
        req.setAttribute("cart", cart);

        // Ir a la vista
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}