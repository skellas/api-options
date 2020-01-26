package com.teachingtechleads.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.teachingtechleads.data.object.Book;
import com.teachingtechleads.data.repository.BookRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = { BookRepository.class })
@EntityScan(basePackageClasses = { Book.class })
public class SpringDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
    }

}
