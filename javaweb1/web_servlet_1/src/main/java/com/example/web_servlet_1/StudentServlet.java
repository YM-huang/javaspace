package com.example.web_servlet_1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String stuid = request.getParameter("stuid");
        String name = request.getParameter("name");
        String major = request.getParameter("major");
        student stu = new student(stuid,name,major);

        HttpSession session = request.getSession();
        synchronized(session) {
            session.setAttribute("student", stu);
        }
        RequestDispatcher rd =
                request.getRequestDispatcher("/displayStudent.jsp");
        rd.forward(request,response);
    }
}

