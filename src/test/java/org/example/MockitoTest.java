package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Mock
    private Connection con;

    @Mock
    private ResultSet rs;

    @Mock
    private Statement stmt, stmt1;


    @Test
    void test() throws Exception {
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        try (MockedStatic<DBConnection> db = mockStatic(DBConnection.class)) {
            db.when(DBConnection::getConnection).thenReturn(con);

            Room r = new Room();
            assertEquals(0, r.model.getRowCount());
        }

        verify(con, times(1)).createStatement();
        verify(stmt).executeQuery(anyString());
        verify(rs).next();

        verify(stmt1, never()).executeQuery(anyString());
    }
}
