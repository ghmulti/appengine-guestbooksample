package com.ghmulti.appengine;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class GuestbookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("testing") == null) {
            resp.setContentType("text/plain");
            resp.getWriter().println("Hello, this is a testing servlet. \n\n");
            Properties p = System.getProperties();
            p.list(resp.getWriter());

        } else {
            UserService userService = UserServiceFactory.getUserService();
            User currentUser = userService.getCurrentUser();

            if (currentUser != null) {
                resp.setContentType("text/plain");
                resp.getWriter().println("Hello, " + currentUser.getNickname());
            } else {
                resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
