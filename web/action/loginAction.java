/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import entity.AppUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserModel;
import web.ViewManager;

/**
 *
 * @author entel
 */
public class loginAction implements Action {
    private UserModel userModel;

    public loginAction(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {

            req.setAttribute("error", "Email and password are required");
            ViewManager.nextView(req, resp, "/view/login.jsp");
            return;
        }

        AppUser user = userModel.login(email, password);

        if (user == null) {
            req.setAttribute("error", "Invalid email or password");
            ViewManager.nextView(req, resp, "/view/login.jsp");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        ViewManager.nextView(req, resp, "/init.do");
    }
}
