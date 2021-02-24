package ru.softwarecom.uspn.emulators.ecasa.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class DataConfiguration {

    @Bean
    @Profile("h2")
    public DataSource dataSource(DataSourceProperties properties) {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource(
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword()
        );
        dataSource.setDriverClassName(properties.getDriverClassName());
        return dataSource;
    }
}
