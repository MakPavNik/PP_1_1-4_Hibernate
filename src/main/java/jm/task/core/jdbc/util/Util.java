package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public Connection connect() {
        String url = "jdbc:mysql://localhost:3306/schema_01";
        String userName = "root";
        String password = "12345";


        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}