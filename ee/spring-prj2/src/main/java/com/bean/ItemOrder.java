package com.bean;

public class ItemOrder implements IItemOrder {
    private IItem item;
    private int numItems;
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
        this.numItems = numItems;
    }
}
