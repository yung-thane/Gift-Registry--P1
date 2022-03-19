package com.revature.musica;

public class Item {
    private int id;
    private String name;
    

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Item() {
    }
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + "]"; 
    }
}
