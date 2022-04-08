package com.edu.zjut.cn.Observer;

import com.edu.zjut.cn.Interface.Observer;
import com.edu.zjut.cn.Subject.PriceDateSubject;

/**
 * @program: observer2
 * @description: TimeCraphObserver
 * @author: hym(huangyimiao666 @ gmail.com)
 * @create: 2022-04-07 20:21
 **/
public class TimeCraphObserver implements Observer,Runnable{

    PriceDateSubject everyPrice;
    /**
    * @Description: 
    * @Param: [everyPrice]
    * @return: 
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:22 2022/4/7
    */
    public TimeCraphObserver(PriceDateSubject everyPrice) {
        // TODO Auto-generated constructor stub
        this.everyPrice = everyPrice;
    }

    /**
    * @Description: 
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:22 2022/4/7
    */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            everyPrice.readPrice();//调用读取方法
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
    * @Description: 
    * @Param: [everyPrice]
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:22 2022/4/7
    */
    @Override
    public void update(float everyPrice) {
        // TODO Auto-generated method stub
        //this.everyPrice=everyPrice;
    }
}
