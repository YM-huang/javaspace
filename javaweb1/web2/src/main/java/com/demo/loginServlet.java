package com.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = { "/login" })
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (userName != null && userName.equals("admin")
                && password != null && password.equals("123456")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", Boolean.TRUE);
            response.sendRedirect("download");
            return;
        } else {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
