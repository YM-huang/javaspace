package com.edu.zjut.cn.Subject;

import com.edu.zjut.cn.Interface.Observer;
import com.edu.zjut.cn.Interface.Subject;

import java.util.ArrayList;

/**
 * @program: observer2
 * @description: PriceDateSubject
 * @author: hym(huangyimiao666 @ gmail.com)
 * @create: 2022-04-07 20:23
 **/
public class PriceDateSubject implements Subject {

    @SuppressWarnings("rawtypes")
    private ArrayList observers;
    private float price;
    // �ж����ɻ��Ƕ�ȡ
    boolean flag = false;

    /**
    * @Description:
    * @Param: []
    * @return: float
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:26 2022/4/7
    */
    public float getPrice() {
        return price;
    }

    /**
    * @Description:
    * @Param: [price]
    * @return: float
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:28 2022/4/7
    */
    public float setPrice(float price) {
        return this.price = price;
    }

    /**
    * @Description: �������������������
    * @Param: []
    * @return: float
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:28 2022/4/7
    */
    public synchronized float generatePrice() {
        // ����Ѿ����������ݾ͵ȴ�
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        float i=this.setPrice((float) (Math.random() * 100));
        System.out.println("���ɼ۸�:" + i);
        // ��ʾ�Ѿ�����������
        flag = true;
        notifyAll();// ���ѽ���
        return (float) i;
    }

    /**
    * @Description: ��ȡ��Ϣ
    * @Param: []
    * @return: int
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:28 2022/4/7
    */
    public synchronized int readPrice() {
        if (!flag) { // ���û���κ�������ȴ�
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("��ȡ�۸�:" + this.getPrice() );
        flag = false;// ��ʾ�õ�������
        notifyAll();
        return 0;
    }

    /**
    * @Description:
    * @Param: [o]
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:28 2022/4/7
    */
    @SuppressWarnings("unchecked")
    @Override
    public void registerObserver(Observer o) {
        // TODO Auto-generated method stub
        observers.add(o);
    }

    /**
    * @Description:
    * @Param: [o]
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:28 2022/4/7
    */
    @Override
    public void removeObserver(Observer o) {
        // TODO Auto-generated method stub
        int i = observers.indexOf(o);
        if (i >= 0)
        {
            observers.remove(i);
        }
    }

    /**
    * @Description:
    * @Param: []
    * @return: void
    * @Author: hym(huangyimiao666@gmail.com)
    * @Date: 20:28 2022/4/7
    */
    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        for (int i = 0; i< observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(price);
        }
    }

}
