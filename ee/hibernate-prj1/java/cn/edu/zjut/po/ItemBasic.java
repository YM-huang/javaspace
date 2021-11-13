package cn.edu.zjut.po;

public class ItemBasic {
    private String itemID;
    private String title;
    private String description;
    private Float cost;

    public ItemBasic() {
    }

    public ItemBasic(String itemID) {
        this.itemID = itemID;
    }

    public ItemBasic(String itemID, String title, String description, Float cost) {
        this.itemID = itemID;
        this.title = title;
        this.description = description;
        this.cost = cost;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
