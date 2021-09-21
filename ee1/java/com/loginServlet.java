package com;

import com.Dao.userDao;
import com.model.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    boolean checkUser(UserBean user){
        userDao ud = new userDao();
        if(ud.searchUser(user)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type1");
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);
        user.setType(type);
        if(checkUser(user)){
            request.setAttribute("USER",user);
            RequestDispatcher rd = request.getRequestDispatcher("/loginsuccess.jsp");
            rd.forward(request,response);
        }else{
            response.sendRedirect("/loginfailed.jsp");
        }
    }
}
