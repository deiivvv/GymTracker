package com.dad.gymtracker.Configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private AppConfig appConfig;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(appConfig.getDatasourceUrl());
        dataSource.setUsername(appConfig.getDatasourceUsername());
        dataSource.setPassword(appConfig.getDatasourcePassword());
        return dataSource;
    }
}
