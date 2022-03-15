package com.revature.musica;

import javax.sql.DataSource;

public class DbDataSource {
    private DataSource ds;

    public DbDataSource() {
    }

    public DbDataSource(DataSource ds) {
        this.ds = ds;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

}
