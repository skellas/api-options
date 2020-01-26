package com.teachingtechleads.data.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.teachingtechleads.data.object.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository uut; // Unit Under Test

    @Test
    public void shouldFindAll() {
        // Given
        uut.save(Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build());
        uut.save(Book.builder().author("Pat Kua").title("Talking with Tech Leads").build());
        uut.save(Book.builder().author("Camille Fournier").title("The Manager's Path").build());

        // When
        final List<Book> books = uut.findAll();

        // Then
        assertThat(books.size(), equalTo(3));

    }

    @Test
    public void shouldFindBookById() {
        // Given
        final Book book = Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build();

        // When
        uut.save(book);

        // Then
        assertThat(uut.findById(book.getId()).get().getAuthor(), equalTo(book.getAuthor()));
    }

    @Test
    public void shouldSaveBook() {
        // Given
        final Book book = Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build();

        // When
        uut.save(book);

        // Then
        assertThat(uut.count(), Matchers.equalTo(1L));
    }

}
