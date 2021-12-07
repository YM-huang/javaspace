package com.bean;

public interface IItemOrder {
    void incrementNumItems();

    void cancelOrder();

    double getTotalCost();

    IItem getItem();

    void setItem(IItem item);

    int getNumItems();

    int getUnitCost();

    void setNumItems(int numItems);
}
