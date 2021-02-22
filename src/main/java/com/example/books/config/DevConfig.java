package com.example.books.config;

import com.example.books.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService _dbService;

    @Bean
    public boolean instanceDatabase() throws ParseException {
        _dbService.databaseInstance();
        return true;
    }
}
