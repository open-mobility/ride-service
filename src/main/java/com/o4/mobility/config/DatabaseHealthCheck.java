package com.o4.mobility.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
public class DatabaseHealthCheck implements ApplicationRunner {

    private final DataSource dataSource;
    private final ApplicationContext context;

    public DatabaseHealthCheck(DataSource dataSource, ApplicationContext context) {
        this.dataSource = dataSource;
        this.context = context;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean exit = false;
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                log.info("Database connection is valid.");
            } else {
                log.error("Database connection is not valid.");
               exit = true;

            }
        } catch (SQLException e) {
            log.error("Failed to connect to the database.", e);
            exit = true;
        }

        if (exit) {
            log.error("Exiting system .... db error");
            ((ConfigurableApplicationContext) context).close();
        }
    }
}
