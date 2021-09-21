package com.example.web_servlet_1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "regist.do", value = "/regist.do")
public class registdo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\info.txt")));
        boolean boo=false;
        String strLine = "";
        while (br.ready()) {
            strLine = br.readLine();
            if(strLine.equals("")) {
                break;
            }
            if(strLine.substring(0,strLine.indexOf('|')).equals(userName)){
                boo=true;
                break;
            }
        }
        if(boo)
        {
            out.print("<html><body>");
            out.println("<h1>" + "this user_name has already exist!"+"</h1>");
            out.println("</body><ml>");
        }
        else{
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\info.txt", true)));
            //使用缓冲区的方法将数据写入到缓冲区中,下同
            if (userName == null) {
                throw new IllegalArgumentException("userName is null");
            }
            bfw.write(userName);
            bfw.write('|');
            if (password == null) {
                throw new IllegalArgumentException("password is null");
            }
            String sha = getSHA256Str(password);
            bfw.write(sha);
            bfw.write('|');
            if (name == null) {
                throw new IllegalArgumentException("name is null");
            }
            bfw.write(name);
            bfw.write('|');
            if (sex == null) {
                throw new IllegalArgumentException("sex is null");
            }
            bfw.write(sex);
            bfw.write('|');
            if (email == null) {
                throw new IllegalArgumentException("email is null");
            }
            bfw.write(email);
            bfw.write('|');
            if (phone == null) {
                throw new IllegalArgumentException("phone is null");
            }
            bfw.write(phone);
            bfw.newLine();
            bfw.flush(); //使用缓冲区中的方法，将数据刷新到目的地文件中去
            bfw.close();//关闭缓冲区,同时关闭fw流对象


            out.print("<html><body>");
            out.println("<h1>" + "successful register!go <a href=\"http://localhost/login.html\">login</a>" + "</h1>");
            out.println("</body><ml>");
        }
    }

    public static String getSHA256Str(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
