/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.AppUser;
import entity.Product;
import entity.PurchaseItem;
import entity.PurchaseOrder;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductModel;
import model.PurchaseModel;
import web.ViewManager;
/**
 *
 * @author entel
 */
public class purchaseAction implements Action {

    private ProductModel productModel;
    private PurchaseModel purchaseModel;

    public purchaseAction(ProductModel productModel, PurchaseModel purchaseModel) {
        this.productModel = productModel;
        this.purchaseModel = purchaseModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        Boolean started = (Boolean) session.getAttribute("paypalCheckoutStarted");
        if (started == null || !started) {
            ViewManager.nextView(req, resp, "/view/cart.jsp");
            return;
        }

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        AppUser user = (AppUser) session.getAttribute("user");

        if (cart != null && cart.getItems() != null) {
            if (user != null) {
                PurchaseOrder order = new PurchaseOrder();
                order.setUser(user);
                order.setPurchaseDate(new Date());
                order.setTotal(cart.getTotal());

                purchaseModel.createOrder(order);

                for (ShoppingCartItem item : cart.getItems()) {
                    PurchaseItem purchaseItem = new PurchaseItem();
                    purchaseItem.setOrder(order);
                    purchaseItem.setProduct(item.getProduct());
                    purchaseItem.setQuantity(item.getAmount());
                    purchaseItem.setUnitPrice(item.getProduct().getPrice());

                    purchaseModel.createItem(purchaseItem);
                }
            }
            
            for (ShoppingCartItem item : cart.getItems()) {
                Product p = item.getProduct();

                int newStock = p.getStock() - item.getAmount();
                if (newStock < 0) {
                    newStock = 0;
                }

                p.setStock(newStock);
                productModel.update(p);
            }

            cart.clear();
            session.setAttribute("cart", cart);
        }

        session.removeAttribute("paypalCheckoutStarted");

        req.setAttribute("cart", cart);
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}