/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import entity.AppUser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PurchaseModel;
import entity.PurchaseOrder;
import web.ViewManager;

/**
 *
 * @author entel
 */
public class historyAction implements Action {

    private PurchaseModel purchaseModel;

    public historyAction(PurchaseModel purchaseModel) {
        this.purchaseModel = purchaseModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        AppUser user = (AppUser) session.getAttribute("user");

        if (user == null) {
            ViewManager.nextView(req, resp, "/view/login.jsp");
            return;
        }

        List<PurchaseOrder> orders = purchaseModel.findOrdersByUser(user);
        req.setAttribute("orders", orders);

        ViewManager.nextView(req, resp, "/view/history.jsp");
    }
}