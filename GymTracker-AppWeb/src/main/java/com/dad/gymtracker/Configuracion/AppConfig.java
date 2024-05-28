package com.dad.gymtracker.Configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${SPRING_DATASOURCE_URL:jdbc:mysql://localhost:3306/gymtracker}")
    private String datasourceUrl;

    @Value("${SPRING_DATASOURCE_USERNAME:dad}")
    private String datasourceUsername;

    @Value("${SPRING_DATASOURCE_PASSWORD:padre}")
    private String datasourcePassword;

    @Value("${SERVER_PORT:443}")
    private String serverPort;

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public String getServerPort() {
        return serverPort;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "datasourceUrl='" + datasourceUrl + '\'' +
                ", datasourceUsername='" + datasourceUsername + '\'' +
                ", datasourcePassword='" + datasourcePassword + '\'' +
                ", serverPort='" + serverPort + '\'' +
                '}';
    }
}
