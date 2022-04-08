package com.edu.zjut.cn.Interface;

/**
 * @program: observer2
 * @description: 观察者接口
 * @author: hym(huangyimiao666 @ gmail.com)
 * @create: 2022-04-07 20:11
 **/
public interface Observer {

    /**
    * @Description: 当主题状态改变时,会将一个String类型字符传入该方法的参数,每个观察者都需要实现该方法
    * @Param: [price]
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:14 2022/4/7
    */
    public void update(float price);
}
