package cn.edu.zjut.po;

import java.sql.Blob;

public class ItemDetail  extends ItemBasic{
    private Blob image;

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public ItemDetail() {
    }

    public ItemDetail(String itemID, String title, String description, Float cost, Blob image) {
        super(itemID, title, description, cost);
        this.image = image;
    }
}
