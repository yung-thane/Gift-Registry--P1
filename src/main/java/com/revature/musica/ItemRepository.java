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
                        try {
                            //Creates result set.
                            ResultSet rs = connection.prepareStatement("select * from Item").executeQuery();
                            while(rs.next()){
                                items.add(new Item(rs.getInt("ItemId"), rs.getString("Name")));
                            }
                } catch (SQLException e) {
                    System.err.println("Failed to retrieve from db: " + e.getSQLState());
                }
                return items;
    }

    public void insertItem(Item newItem) {
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into Item values(?, ?)");
            stmt.setInt(1, newItem.getItemId());
            stmt.setString(2, newItem.getName());
            //Can't forget to execute statement, or it will just not happen.
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }

    public void deleteItem(Item newItem) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from Item where id = ?");
            stmt.setInt(1, newItem.getItemId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to delete: " + e.getMessage());
        }
    }

}
