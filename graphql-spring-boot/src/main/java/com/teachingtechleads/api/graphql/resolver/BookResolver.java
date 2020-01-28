package com.teachingtechleads.api.graphql.resolver;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.teachingtechleads.data.object.Book;
import com.teachingtechleads.data.repository.BookRepository;

@Component
public class BookResolver implements GraphQLQueryResolver {
    private final BookRepository bookRepository;

    @Autowired
    public BookResolver(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book book(final Long id) {
        final Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public Collection<Book> books() {
        return bookRepository.findAll();
    }
}
