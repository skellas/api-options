package com.teachingtechleads.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teachingtechleads.data.object.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
