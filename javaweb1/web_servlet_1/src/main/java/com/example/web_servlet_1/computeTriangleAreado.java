package com.example.web_servlet_1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "computeTriangleArea.do", value = "/computeTriangleArea.do")
public class computeTriangleAreado extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Sfirst = request.getParameter("first");
        String Ssecond = request.getParameter("second");
        String Sthird = request.getParameter("third");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        try {
            double first = Double.parseDouble(Sfirst);
            double second = Double.parseDouble(Ssecond);
            double third = Double.parseDouble(Sthird);
            double p = (first+second+third)/2;
            double result = Math.sqrt(p*(p-first)*(p-second)*(p-third));
            String res = String.format("%.2f", result);
            if ((first+second > third) && (third+second > first) && (third+first > second)) {
                out.println("三角形的面积为：" + res);
            } else {
                out.println("错误：三条边长无法构成三角形!");
            }
        }catch(NumberFormatException nfe)
        {
            out.println("错误：请输入数字！");
        }

        out.println("</body></html>");
    }
}
