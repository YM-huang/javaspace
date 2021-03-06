package cn.edu.zjut.action;

import cn.edu.zjut.bean.ShoppingCart;
import cn.edu.zjut.bean.UserBean;
import cn.edu.zjut.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;
import java.util.Map;

public class UserAction extends ActionSupport {

    private Map request,session,application;
    public void setRequest(Map<String ,Object>request){
        this.request=request;
    }
    public void setSession(Map<String,Object>session){
        this.session=session;
    }

    public void setApplication(Map application) {
        this.application = application;
    }
    private static Integer count=0;
    public UserAction(){
        System.out.println(" 创建了一个 UserAction  类对象。");
    }
    public Integer getCount() {
        return count;
    }
    private UserBean loginUser;
    private UserBean regUser;
    public UserBean getLoginUser() {
        return loginUser;
    }
    public UserBean getRegUser() {
        return regUser;
    }
    public void setLoginUser(UserBean loginUser) {
        this.loginUser = loginUser;
    }
    public void setRegUser(UserBean regUser) {
        this.regUser = regUser;
    }
    public String login(){
//        count++; //Action
//        application.setAttribute("counter",count);
        //获取ActionContext对象
        ActionContext ctx = ActionContext.getContext();
        //通过ActionContext对象获取请求、会话和上下文对象相关联的Map对象
        request =(Map) ctx.get("request");
        session =(Map) ctx.getSession();
        application = (Map) ctx.getApplication();
        //访问application范围的属性值
//        Integer counter = (Integer)application.get("counter");
//        UserService userService = new UserService();
//        if(userService.login(loginUser)){
//            this.addActionMessage(this.getText("login.success.label"));
//            return "success";
//        } else {
//            this.addActionError(this.getText("login.error"));
//            return "fail";
//        }
//        if(counter==null) {
//            counter = 1;
//        }
//        else {
//            counter = counter + 1;
//        }
//        //设置 application 范围的属性
//        application.put("counter", counter);
//        UserService userServ = new UserService();
//        if (userServ.login(loginUser)) {
//            //设置 session 范围的属性
//            session.put("user", loginUser.getAccount());
//            //设置 request 范围的属性
//            request.put("tip", " 您已登录成功");
//            return "success";
//        } else {
//            return "fail";
//        }

        //访问application范围的属性值
        Integer counter = (Integer)application.get("counter");
        ShoppingCart shoppingCart = new ShoppingCart();
        session.put("shoppingcart",shoppingCart);
        if(counter==null) {
            counter = 1;
        }
        else {
            counter += 1;
        }
        //设置application范围的属性
        application.put("counter",counter);
        UserService userService = new UserService();
        if(userService.login(loginUser)){
            session.put("user",loginUser.getAccount());
            request.put("tip","您已登录成功");
            return "success";
        }else{
            return "fail";
        }
    }
    public String register(){
        UserService userService = new UserService();
        if(userService.register(regUser)){
            return "success";
        }
        return "fail";
    }

//    @Override
//    public void validate() {
//        String account = loginUser.getAccount();
//        String pwd = loginUser.getPassword();
//        if (account == null || account.equals("")) {
//            this.addFieldError("loginUser.account",
//                    this.getText("login.account.null"));
//        }
//        if (pwd == null || pwd.equals("")) {
//            this.addFieldError("loginUser.password",
//                    this.getText("login.password.null"));
//        }
//    }
    public void validateLogin(){
    //        UserService userService = new UserService();
        String account = loginUser.getAccount();
        String pwd = loginUser.getPassword();
        if(account == null || account.equals("")) {
            this.addFieldError("loginUser.account", this.getText("login.account.null"));
        }
        if(pwd == null || pwd.equals("")) {
            this.addFieldError("loginUser.password", this.getText("login.password.null"));
        }
    }
    public void validateRegister(){
        String account = regUser.getAccount();
        String pwd = regUser.getPassword();
        String repwd = regUser.getRepassword();
        String email =  regUser.getEmail();
        String sex = regUser.getSex();
        Date birthdate = regUser.getBirthday();
        if(account == null || account.equals("")) {
            this.addFieldError("regUser.account", this.getText("login.account.null"));
        }
        if(pwd == null || pwd.equals("")) {
            this.addFieldError("regUser.password", this.getText("login.password.null"));
        }
        if(birthdate == null || birthdate.equals("")) {
            this.addFieldError("regUser.birthday", this.getText("生日必须是日期，并符合“yyyy-mm-dd”格式"));
        }
    }

    @Override
    public String execute() {
        UserService userService = new UserService();
        if(loginUser !=null){
            if(userService.login(loginUser)){
                return "success";
            }
        }
        else{
            if(userService.register(regUser)){
                return "success";
            }
        }


        return "fail";
    }
}
