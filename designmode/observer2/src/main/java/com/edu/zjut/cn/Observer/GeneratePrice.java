package com.edu.zjut.cn.Observer;

import com.edu.zjut.cn.Subject.PriceDateSubject;

/**
 * @program: observer2
 * @description: 生成数据的线程类
 * @author: hym(huangyimiao666 @ gmail.com)
 * @create: 2022-04-07 20:19
 **/
public class GeneratePrice implements Runnable { 
    PriceDateSubject price;

    /**
    * @Description: 
    * @Param: [price]
    * @return: 
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:20 2022/4/7
    */
    public GeneratePrice(PriceDateSubject price) {
        this.price = price;
    }

    /**
    * @Description: 
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:20 2022/4/7
    */
    @Override
    public void run() {
        while (true) {
            price.generatePrice();// 调用生成方法

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
