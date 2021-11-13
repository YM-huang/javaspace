package cn.edu.zjut.service;
import cn.edu.zjut.bean.UserBean;
public class UserService {
    public boolean login(UserBean loginUser) {
        if (loginUser.getAccount().equals(loginUser.getPassword())) {
            return true;
        }
        return false;
    }
}
