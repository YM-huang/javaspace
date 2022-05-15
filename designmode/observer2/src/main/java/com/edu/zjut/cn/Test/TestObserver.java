package com.edu.zjut.cn.Test;

import com.edu.zjut.cn.Observer.GeneratePrice;
import com.edu.zjut.cn.Observer.TimeCraphObserver;
import com.edu.zjut.cn.Subject.PriceDateSubject;

/**
 * @program: observer2
 * @description: TestObserver
 * @author: hym(huangyimiao666 @ gmail.com)
 * @create: 2022-04-07 20:35
 **/
public class TestObserver {
    private static final Drawing dw = new Drawing();
    public static void main(String[] args) {
        PriceDateSubject price=new PriceDateSubject();

        new Thread(new GeneratePrice(price)).start();
        new Thread(new TimeCraphObserver(price)).start();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                dw.setVisible(true);
            }
        });
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    long t = System.currentTimeMillis();//当前系统的时间
                    float v = price.generatePrice();
                    dw.addData(t, v);
                    dw.repaint();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.run();
    }
}
