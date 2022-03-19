package com.revature.musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private Connection connection;

    public ItemRepository() {
    }

    public ItemRepository(Connection connection) {
        this.connection = connection;
    }
    
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        System.out.println("get, made arraylist");
        
                        try {
                            //Creates result set.
                            ResultSet rs = connection.prepareStatement("select * from Item").executeQuery();
                            System.out.println("try SQL select from item statement");
                            
                            while(rs.next()){
                                items.add(new Item(rs.getInt("id"), rs.getString("Name")));
                                System.out.println("while, add item.add(get int(getid),...)");
                            }
                } catch (SQLException e) {
                    System.err.println("Failed to retrieve from db: " + e.getSQLState());
                }
                return items;
    }

    public void insertItem(Item newItem) {
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into Item values(?, ?)");
            System.out.println("try, insert stmt insert into Item (??)");
            stmt.setInt(1, newItem.getid());
            System.out.println("try, setInt.newitem.getid");
            stmt.setString(2, newItem.getName());
            System.out.println("try, setString.getName");
            //Can't forget to execute statement, or it will just not happen.
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }

    public void deleteItem(Item newItem) {
        try {
            System.out.println("first line of deleteItem{try{}}");
            System.out.println("inside delete: " + newItem);
            PreparedStatement stmt = connection.prepareStatement("delete from Item where item.id = ?");
            System.out.println("after delete statement");
            stmt.setInt(1, newItem.getid());
            System.out.println("after setInt statement}");
            stmt.executeUpdate();
            System.out.println("after execute update");
        } catch (SQLException e) {
            System.err.println("Failed to delete: " + e.getMessage());
        }
    }

}
