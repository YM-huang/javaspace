package cn.edu.zjut.service;
import java.util.List;
import cn.edu.zjut.po.Customer;
import cn.edu.zjut.dao.CustomerDAO;
public class UserService {

    public boolean login(Customer loginUser) {
        String account = loginUser.getAccount();
        String password = loginUser.getPassword();
        String hql = "from Customer as user where account='"
                +account+ "' and password='" + password +"'";
        CustomerDAO dao = new CustomerDAO();
        List list = dao.findByHql(hql);
        if(list.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public void register(Customer loginUser) {
        CustomerDAO dao = new CustomerDAO();
        dao.save(loginUser);
    }
}
