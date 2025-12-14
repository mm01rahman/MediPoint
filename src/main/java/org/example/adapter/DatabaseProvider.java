package org.example.adapter;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseProvider {
    Connection getConnection() throws SQLException;
}
