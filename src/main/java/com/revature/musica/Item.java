package com.revature.musica;

public class Item {
    private int itemId;
    private String name;

    public Item(int itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }
    public Item() {
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", name=" + name + "]"; 
    }
}
