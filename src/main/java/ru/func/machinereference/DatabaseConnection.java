package ru.func.machinereference;

import ru.func.machinereference.dao.CarDao;
import ru.func.machinereference.dao.impl.CarDaoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author func 01.01.2020
 */
public class DatabaseConnection {
    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException, IOException {
        Properties prop = new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sql.properties"));

        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection(
                "jdbc:mysql://" +
                        prop.getProperty("database.host") + ":" +
                        prop.getProperty("database.port") + "/" +
                        prop.getProperty("database.name"),
                prop.getProperty("database.user"),
                prop.getProperty("database.password")
        );
    }
}