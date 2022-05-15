package com.edu.zjut.cn.Interface;

/**
 * @program: observer2
 * @description: 被观察对象实例
 * @author: hym(huangyimiao666 @ gmail.com)
 * @create: 2022-04-07 20:14
 **/
public interface Subject {
    /**
    * @Description: addObserver
    * @Param: [o]
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:15 2022/4/7
    */
    public void registerObserver(Observer o);
    /**
    * @Description: deleteObserver
    * @Param: [o]
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:15 2022/4/7
    */
    public void removeObserver(Observer o);
    /**
    * @Description: 
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:15 2022/4/7
    */
    public void notifyObservers();
}
