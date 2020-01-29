package com.teachingtechleads.api.graphql.resolver;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.teachingtechleads.api.graphql.type.BookInput;
import com.teachingtechleads.data.object.Book;
import com.teachingtechleads.data.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookResolverTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookResolver uut;

    @Test
    public void shouldCallFindAll() {
        // When
        uut.books();

        // Then
        verify(bookRepository).findAll();
    }

    @Test
    public void shouldCallFindById() {
        // Given
        final Book book = Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build();
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));

        // When
        final Book result = uut.book(1L);

        // Then
        verify(bookRepository).findById(1L);
        assertThat(result, equalTo(book));
    }

    @Test
    public void shouldReturnCreatedBook() {
        // Given
        final Book book = Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build();
        given(bookRepository.save(book)).willReturn(book.toBuilder().id(1L).build());
        final BookInput bookInput = BookInput.builder().author("Seth Kellas").title("Teaching Tech Leads").build();

        // When
        final Book returnedBook = uut.createBook(bookInput);

        // Then
        assertThat(returnedBook.getId(), equalTo(1L));
        assertThat(returnedBook.getAuthor(), equalTo(book.getAuthor()));
        assertThat(returnedBook.getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldReturnNullWhenNotFound() {
        // Given
        given(bookRepository.findById(1L)).willReturn(Optional.empty());

        // When
        final Book result = uut.book(1L);

        // Then
        verify(bookRepository).findById(1L);
        assertThat(result, Matchers.nullValue());
    }

    @Test
    public void shouldTransformBookInput() {
        // Given
        final Book book = Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build();
        given(bookRepository.save(book)).willReturn(book.toBuilder().id(1L).build());
        final BookInput bookInput = BookInput.builder().author("Seth Kellas").title("Teaching Tech Leads").build();

        // When
        uut.createBook(bookInput);

        // Then
        verify(bookRepository).save(book);
    }

}
