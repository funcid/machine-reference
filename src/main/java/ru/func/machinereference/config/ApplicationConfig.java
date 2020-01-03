package ru.func.machinereference.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class ApplicationConfig {

    @Bean
    public Connection connection() throws IOException, ClassNotFoundException, SQLException {
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
