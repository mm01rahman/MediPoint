package org.example.adapter;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Adapter keeping legacy DBConnection behind a minimal provider interface.
 */
public class DatabaseAdapter implements DatabaseProvider {
    @Override
    public Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }
}
