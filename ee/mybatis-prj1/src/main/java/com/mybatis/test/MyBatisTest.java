package com.mybatis.test;

import com.mybatis.po.MyUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class MybatisTest {
    public static void main(String[] args){
        try {
            InputStream config= Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory ssf= new SqlSessionFactoryBuilder().build(config);
            SqlSession ss=ssf.openSession();
//查询一个用户
            MyUser mu = ss.selectOne("com.mybatis.mapper.UserMapper.selectUserById", 1123);
            System.out.println(mu);
//添加一个用户
//            MyUser addmu=new MyUser();
//            addmu.setUid(1123);
//            addmu.setUname("张三");
//            addmu.setUsex("男");
//            ss.insert("com.mybatis.mapper.UserMapper.addUser",addmu);
//修改一个用户

//删除一个用户

//查询所有用户
            List<MyUser> ListMu=ss.selectList("com.mybatis.mapper.UserMapper.selectAllUser");
            for(MyUser myuser: ListMu){
                System.out.println(myuser);
            }

            ss.commit();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
