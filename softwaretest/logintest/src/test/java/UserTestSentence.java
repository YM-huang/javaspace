import com.edu.zjut.logintest.LoginServlet;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class UserTestSentence {
    LoginServlet servlet = new LoginServlet();
    //创建req
    HttpServletRequest mockRequest;
    //创建res
    HttpServletResponse mockResponse;

    //异常信息
    interface ExceptionMsg {
        String LOGIN_SUCCESS = "Login success.";
        String LOGIN_FAIL = "Login failed.";

    }

    enum Type {
        Success, Fail
    }
    Type type;
    String inputUserName;
    String inputPassword;
    String expected;

    /**
    * @Description: UserTestSentence
    * @Param: [type, inputUserName, inputPassword, expected]
    * @return: 
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 19:29 2022/4/24
    */
    public UserTestSentence(Type type, String inputUserName, String inputPassword, String expected) {
        this.type = type;
        this.inputUserName = inputUserName;
        this.inputPassword = inputPassword;
        this.expected = expected;
        System.out.println("Type= " + type + "; username = " + inputUserName + "; password = " + inputPassword + "; expected = " + expected);
    }


    /**
    * @Description: 数据填写
    * @Param: []
    * @return: java.util.Collection
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 19:30 2022/4/24
    */
    @Parameterized.Parameters
    public static Collection args() {
        return Arrays.asList(new Object[][]{
                {Type.Success, "admin", "123456", ExceptionMsg.LOGIN_SUCCESS},
                {Type.Fail, "hmm", "123456", ExceptionMsg.LOGIN_FAIL},
                {Type.Fail, "admin", "654321", ExceptionMsg.LOGIN_FAIL},
                {Type.Fail, null, "123456", ExceptionMsg.LOGIN_FAIL},
                {Type.Fail, null, "654321", ExceptionMsg.LOGIN_FAIL},
                {Type.Fail, "admin", null, ExceptionMsg.LOGIN_FAIL},
                {Type.Fail, "adm", null, ExceptionMsg.LOGIN_FAIL},
                {Type.Fail, null, null, ExceptionMsg.LOGIN_FAIL},
        });
    }

    /**
    * @Description: 初始化easymock
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 19:30 2022/4/24
    */
    @Before
    public void init() {
        mockRequest = EasyMock.createStrictMock(HttpServletRequest.class);
        mockResponse = EasyMock.createStrictMock(HttpServletResponse.class);
    }

    /**
    * @Description: 测试成功
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 19:30 2022/4/24
    */
    @Test
    public void testLoginSuccess() {
        Assume.assumeTrue(type == Type.Success);
        EasyMock.expect(mockRequest.getParameter("username")).andReturn(inputUserName);
        EasyMock.expect(mockRequest.getParameter("password")).andReturn(inputPassword);
        EasyMock.replay(mockRequest, mockResponse);
        try {
            servlet.doPost(mockRequest, mockResponse);
            EasyMock.verify(mockRequest);
            if (!expected.equals(ExceptionMsg.LOGIN_SUCCESS)) {
                Assert.fail("no exception throw");
            }
        } catch (Exception e) {
            if (!expected.equals(ExceptionMsg.LOGIN_SUCCESS)) {
                Assert.assertEquals(this.expected, e.getMessage());
            }

        }

    }

    /**
    * @Description: 测试失败
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 19:31 2022/4/24
    */
    @Test
    public void testLoginFailed() {
        Assume.assumeTrue(type == Type.Fail);
        EasyMock.expect(mockRequest.getParameter("username")).andReturn(inputUserName);
        EasyMock.expect(mockRequest.getParameter("password")).andReturn(inputPassword);
        EasyMock.replay(mockRequest, mockResponse);
        try {
            servlet.doPost(mockRequest, mockResponse);
            EasyMock.verify(mockRequest);
            if (!expected.equals(ExceptionMsg.LOGIN_SUCCESS)) {
                Assert.fail("no exception throw");
            }
        } catch (Exception e) {
            if (!expected.equals(ExceptionMsg.LOGIN_SUCCESS)) {
                Assert.assertEquals(this.expected, e.getMessage());
            }

        }
    }

}
