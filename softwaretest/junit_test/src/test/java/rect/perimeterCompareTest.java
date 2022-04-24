package rect;

import com.edu.zjut.main.Rect;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * @program: junit_test
 * @description: junittest
 * @author: hym(huangyimiao666 @ gmail.com)
 * @create: 2022-04-15 14:10
 **/
@RunWith(Parameterized.class)
public class perimeterCompareTest extends TestCase {
    private Rect[] arr;
    private int expected;

    @Before
    public void setUp(){

    }

    public perimeterCompareTest(Rect[] arr, int expected){
        this.arr=arr;
        this.expected=expected;
    }

    /**
    * @Description: data()
    * @Param: []
    * @return: java.lang.Iterable<java.lang.Object[]>
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:47 2022/4/17
    */
    @Parameterized.Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new Rect[] {
                        new Rect(5, 6), new Rect(3, 4)
                },1},
                {new Rect[] {
                        new Rect(-5, 16), new Rect(1, 1)
                },-1},
                {new Rect[] {
                        new Rect(5, 6), new Rect(6, 5)
                },0},
                {new Rect[] {
                        new Rect(-5, -6), new Rect(3, -4)
                },-99},
                {new Rect[] {
                        new Rect(1, 1), new Rect(-3, 14)
                },1},
                {new Rect[] {
                        new Rect(3, 4), new Rect(5, 6)
                },-1}
        });
    }

    /**
    * @Description: testPerimeterCompare()
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:47 2022/4/17
    */
    @Test
    public void testPerimeterCompare(){
        Rect.perimeterCompare pCompare = new Rect.perimeterCompare();
        int temp=pCompare.compare(arr[0], arr[1]);
        assertEquals(temp,expected);
    }

    @After
    public void tearDown(){

    }
}
