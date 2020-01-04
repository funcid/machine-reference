package ru.func.machinereference.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ApplicationConfig {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Bean
    public Connection connection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection(
                "jdbc:mysql://" +
                        dataSourceConfig.getHost() + ":" +
                        dataSourceConfig.getPort() + "/" +
                        dataSourceConfig.getDbName(),
                dataSourceConfig.getUser(),
                dataSourceConfig.getPassword()
        );
    }
}
