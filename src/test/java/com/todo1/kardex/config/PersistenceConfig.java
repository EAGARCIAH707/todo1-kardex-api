package com.todo1.kardex.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
@Profile("test")
public class PersistenceConfig {

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    String driverClassName = "org.postgresql.Driver";
    dataSource.setDriverClassName(driverClassName);
    String url = "jdbc:postgresql://ec2-34-228-154-153.compute-1.amazonaws.com:5432/dbcmq7i78fj8qg";
    dataSource.setUrl(url);
    String username = "yfdthnqkpjayrs";
    dataSource.setUsername(username);
    String password = "0caf15f13865e0e37f1f280d5453d15c3a76648402fd3e903937ff9d60035692";
    dataSource.setPassword(password);
    return dataSource;
  }
}
