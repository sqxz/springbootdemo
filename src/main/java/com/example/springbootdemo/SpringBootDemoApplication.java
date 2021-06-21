package com.example.springbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class SpringBootDemoApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {
        showConnection();
        showData();
    }

    private void showConnection() throws SQLException {
        log.info("dataSource:--->" + dataSource.toString());
        Connection connection = dataSource.getConnection();
        log.info("connection:--->"+ connection.toString());
        connection.close();
    }

    private void showData(){
        jdbcTemplate.queryForList("select * from books")
                .forEach(row -> log.info(row.toString()));
    }

    @Bean
    MessageConverter createMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}
