package com.example.web_servlet_1;

import com.example.model.loginDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "CustomerServlet.do", value = "/CustomerServlet.do")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String custName = request.getParameter("custName");
        String phone = request.getParameter("phone");

        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\customerinfo.txt")));
        boolean boo=false;
        String strLine = "";
        while (br.ready()) {
            strLine = br.readLine();
            if(strLine.equals("")) {
                break;
            }
            if(strLine.substring(0,strLine.indexOf('|')).equals(email)){
                boo=true;
                break;
            }
        }
        if(boo)
        {
            request.getRequestDispatcher("/accountCreatedFailed.jsp").forward(request,
                    response);
        }
        else{
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\customerinfo.txt", true)));
            //使用缓冲区的方法将数据写入到缓冲区中,下同
            if (email == null) {
                throw new IllegalArgumentException("userName is null");
            }
            bfw.write(email);
            bfw.write('|');
            if (password == null) {
                throw new IllegalArgumentException("password is null");
            }
            bfw.write(password);
            bfw.write('|');
            if (custName == null) {
                throw new IllegalArgumentException("name is null");
            }
            bfw.write(custName);
            bfw.write('|');
            if (phone == null) {
                throw new IllegalArgumentException("sex is null");
            }
            bfw.write(phone);
            bfw.write('|');

            bfw.newLine();
            bfw.flush(); //使用缓冲区中的方法，将数据刷新到目的地文件中去
            bfw.close();//关闭缓冲区,同时关闭fw流对象
            loginDB loginDB = (loginDB) getServletContext().getAttribute("loginDB");
            if (loginDB == null) {
                loginDB = new loginDB();
            }
            loginDB.addUser(request.getParameter("email"),
                    request.getParameter("password"),
                    request.getParameter("custName"),
                    request.getParameter("phone"));
            getServletContext().setAttribute("loginDB", loginDB);
            request.getRequestDispatcher("/displayCustomer.jsp").forward(request,
                    response);
        }

    }
}
