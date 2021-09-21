package com.example.testmysql;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "inputServlet", value = "/inputServlet")
public class inputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection dbconn = null;
    @Override
    public void init() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String dburl = "jdbc:mysql://127.0.0.1:3306/huangyimiao?useSSL=true";
        String username = "root";
        String password = "huangyimiao0129";
        try{
            Class.forName(driver);
            // 加载驱动程序
            // 创建连接对象
            dbconn = DriverManager.getConnection(
                    dburl,username,password);
        }catch(ClassNotFoundException e1){
            System.out.println(e1);
            getServletContext().log("驱动程序类找不到！");
        }catch(SQLException e2){
            System.out.println(e2);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String sql = "SELECT hym_Sname FROM hym_Students";
            Statement stmt = dbconn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rset = stmt.executeQuery(sql);
            System.out.println(rset);
//            rset.next();
//            rset.updateString(2, "华为p40 pro手机");
//            // 修改当前行的字段值
//            rset.updateRow(); // 更新当前行
//            PreparedStatement pstmt = dbconn.prepareStatement(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
