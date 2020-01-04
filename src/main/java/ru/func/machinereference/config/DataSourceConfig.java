package ru.func.machinereference.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sql.properties")
@Data
public class DataSourceConfig {

    @Value("${database.host}")
    private String host;

    @Value("${database.port}")
    private String port;

    @Value("${database.name}")
    private String dbName;

    @Value("${database.user}")
    private String user;

    @Value("${database.password}")
    private String password;
}
