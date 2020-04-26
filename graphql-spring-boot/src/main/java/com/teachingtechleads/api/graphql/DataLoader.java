package com.teachingtechleads.api.graphql;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teachingtechleads.data.object.Book;
import com.teachingtechleads.data.repository.BookRepository;

@Service
public class DataLoader {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    private void loadBooks() {
        bookRepository.save(Book.builder().author("Patrick Kua").title("Talking with Tech Leads").description(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ultricies, odio efficitur vehicula scelerisque, erat tortor facilisis ligula, eu aliquet enim sapien sit amet dolor.")
                .build());
        bookRepository.save(Book.builder().author("Camille Fournier").title("Manager's Path").description(
                "Sed vulputate elit ex, sed rutrum enim tristique a. Phasellus faucibus fermentum lectus. Nunc vestibulum arcu ut nulla fermentum posuere.")
                .build());
    }
}
