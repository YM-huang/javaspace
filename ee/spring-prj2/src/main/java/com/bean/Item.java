package com.bean;

public class Item implements IItem{
    private String itemID;
    private String title;
    private String description;
    private double cost;
    public Item(String itemID, String title,
                String description, double cost) {
        this.itemID = itemID;
        this.title = title;
        this.description = description;
        this.cost = cost;
        System.out.println("create Item.");
    }

    @Override
    public String getItemID() {
        return itemID;
    }

    @Override
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }
}
