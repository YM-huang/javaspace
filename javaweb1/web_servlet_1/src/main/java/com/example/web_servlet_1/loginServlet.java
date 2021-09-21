package com.example.web_servlet_1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.model.Customer;
import com.example.model.loginDB;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    private loginDB loginDB1;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loginDB1 = (loginDB) getServletContext().getAttribute("loginDB");
        if (loginDB1 == null) {
            loginDB1 = new loginDB();
            config.getServletContext().setAttribute("loginDB", loginDB1);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        // 从login_form 表单得到值
        String pwd = request.getParameter("password");
        Customer customer = new Customer(email, pwd);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\customerinfo.txt")));
        boolean boo=false;
        String strLine = "";
        while (br.ready()) {
            strLine = br.readLine();
            if(strLine.equals("")) {
                break;
            }
            if(strLine.substring(0,strLine.indexOf('|')).equals(email)&&strLine.substring(strLine.indexOf('|')+1,strLine.indexOf('|',strLine.indexOf('|')+1)).equals(pwd)){
                boo=true;
                break;
            }
        }

        if (boo && customer!= null) {
            //  说明存在用户
            request.getSession().setAttribute("customer", customer);
            // 放到session 里面
            // 成功转发到welcome.jsp
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }  else {
            request.getRequestDispatcher("/loginFailed.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
