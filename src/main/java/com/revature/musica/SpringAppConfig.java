package com.revature.musica;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;

public class SpringAppConfig {

    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://musica-db:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgress");
        return ds;
    }
    
    public ItemRepository itemRepository() { 
        try {
            return new ItemRepository(dataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    public ItemController itemController() { 
        return new ItemController(itemRepository(), mapper());
    }

}
