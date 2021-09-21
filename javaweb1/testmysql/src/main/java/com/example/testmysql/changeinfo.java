package com.example.testmysql;

import java.sql.*;

public class changeinfo {
    private static final long serialVersionUID = 1L;
//    Connection dbconn = null;
//    public void init() {
//        String driver = "com.mysql.cj.jdbc.Driver";
//        String dburl = "jdbc:mysql://127.0.0.1:3306/webstore?useSSL=true";
//        String username = "root";
//        String password = "huangyimiao0129";
//        try{
//            Class.forName(driver); // 加载驱动程序
//            // 创建连接对象
//            dbconn = DriverManager.getConnection(
//                    dburl,username,password);
//        }catch(ClassNotFoundException e1){
//            System.out.println(e1);
////            getServletContext().log("驱动程序类找不到！");
//        }catch(SQLException e2){
//            System.out.println(e2);
//        }
//    }
    public static void main(String[] args){
        Connection dbconn = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String dburl = "jdbc:mysql://localhost:3306/huangyimiao?useSSL=true";
        String username = "root";
        String password = "huangyimiao0129";
        try{
            String userid = null;
            Class.forName(driver); // 加载驱动程序
            // 创建连接对象
            dbconn = DriverManager.getConnection(
                    dburl,username,password);
            String sql = "SELECT hym_Sname FROM hym_Students";
            Statement stmt = dbconn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            ResultSet rset = stmt.executeQuery(sql);
//            System.out.println(rset.getString("hym_Sname"));
//            rset.next();
//            rset.updateString(2, "华为p40 pro手机"); // 修改当前行的字段值
//            rset.updateRow(); // 更新当前行
            while(rset.next())
            {
                userid=rset.getString(1);
            }
            System.out.println(userid);
            dbconn.close();
            rset.close();
//            PreparedStatement pstmt = dbconn.prepareStatement(sql);
        }catch(ClassNotFoundException e1){
            System.out.println(e1);
//            getServletContext().log("驱动程序类找不到！");
        }catch(SQLException e2){
            System.out.println(e2);
        }
    }

}
