package com.bean;

import org.springframework.beans.factory.InitializingBean;

public class ItemOrder implements IItemOrder , InitializingBean {
    private IItem item;
    private int numItems;
    public void init() {
        System.out.println(" 正在执行初始化方法 init...");
    }
    public ItemOrder() {
        System.out.println("Spring 实例化 ItemOrder...");
    }
    @Override
    public void incrementNumItems() {
        setNumItems(getNumItems() + 1);
    }
    @Override
    public void cancelOrder() {
        setNumItems(0);
    }
    @Override
    public double getTotalCost() {
        return (getNumItems() * getUnitCost());
    }

    @Override
    public IItem getItem() {
        return item;
    }

    @Override
    public void setItem(IItem item) {
        System.out.println("Spring 注入 item...");
        this.item = item;
    }

    @Override
    public int getNumItems() {
        return numItems;
    }

    @Override
    public int getUnitCost() {
        return 2;
    }

    @Override
    public void setNumItems(int numItems) {
        System.out.println("Spring 注入 numItems...");
        this.numItems = numItems;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" 正在执行初始化方法 afterPropertiesSet...");
    }
}
