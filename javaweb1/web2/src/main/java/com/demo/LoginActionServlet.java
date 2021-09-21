package com.demo;

import com.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "LoginActionServlet", value = "/LoginActionServlet")
public class LoginActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isRight = false;
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String dataDirectory =
                request.getServletContext().getRealPath("/WEB-INF/out");
        File file = new File(dataDirectory,"user.txt");
        BufferedReader br = null;
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"utf-8");
        br = new BufferedReader(reader);

        String str1 = null;
        str1 = br.readLine();
        while (str1!=null){
            int[] pos = new int[10];
            int cnt = 0;
            for(int i = 0;i < str1.length();i++){
                if(str1.charAt(i)==' '){
                    pos[cnt] = i;
                    cnt++;
                }
            }
            String Id = str1.substring(0,pos[0]);
            String Password = str1.substring(pos[0]+1,pos[1]);
            String Name = str1.substring(pos[1]+1,pos[2]);
            String Type = str1.substring(pos[2]+1);

            if(Id.equals(id) && Password.equals(password)){
                isRight = true;
                User userBean = new User(Id,Password,Name,Type);
                HttpSession session = request.getSession();
                synchronized (session){
                    session.setAttribute("userBean",userBean);
                }
                if(Type.equals("学生")){
                    response.sendRedirect("student.jsp");
                }
                else if(Type.equals("教师")) {
                    response.sendRedirect("teacher.jsp");
                }
                break;
            }
            str1 = br.readLine();
        }
        if(!isRight)
        {
            response.sendRedirect("login2.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
