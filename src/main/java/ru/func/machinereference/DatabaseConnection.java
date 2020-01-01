package ru.func.machinereference;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author func 01.01.2020
 */
class DatabaseConnection {
    static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException, IOException {
        Properties prop = new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sql.properties"));

        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection(
                "jdbc:mysql://" +
                        prop.getProperty("host") + ":" +
                        prop.getProperty("port") + "/" +
                        prop.getProperty("data"),
                prop.getProperty("user"),
                prop.getProperty("password")
        );
    }
}