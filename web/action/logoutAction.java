/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import web.ViewManager;

/**
 *
 * @author entel
 */
public class logoutAction implements Action {

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            resp.sendRedirect(req.getContextPath() + "/init.do");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}