package com.revature.musica;

import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;

public class AppConfig {
    private DbDataSource dbDataSource;
    private ItemRepository itemRepository;
    private ItemController itemController;
    private ObjectMapper mapper;

    public AppConfig(){
        String url = "jdbc:postgresql://musica-db:5432/postgres";
        String username = "postgres";
        String password = "postgres";
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        this.dbDataSource = new DbDataSource(ds);

        try {
            this.itemRepository = new ItemRepository(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.mapper = new ObjectMapper();
        this.itemController = new ItemController(itemRepository, mapper);
    }

    public DbDataSource getDbDataSource() {
        return dbDataSource;
    }

    public void setDbDataSource(DbDataSource dbDataSource) {
        this.dbDataSource = dbDataSource;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemController getItemController() {
        return itemController;
    }

    public void setItemController(ItemController itemController) {
        this.itemController = itemController;
    }
    
}
