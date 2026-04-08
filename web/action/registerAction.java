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
public class registerAction implements Action {
    private UserModel userModel;

    public registerAction(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {

            req.setAttribute("error", "All fields are required");
            ViewManager.nextView(req, resp, "/view/register.jsp");
            return;
        }

        AppUser existingUser = userModel.findByEmail(email);

        if (existingUser != null) {
            req.setAttribute("error", "Email already registered");
            ViewManager.nextView(req, resp, "/view/register.jsp");
            return;
        }

        AppUser user = new AppUser();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userModel.create(user);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        ViewManager.nextView(req, resp, "/init.do");
    }
}
