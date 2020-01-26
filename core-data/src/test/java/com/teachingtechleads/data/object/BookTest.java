package com.teachingtechleads.data.object;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BookTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldExposeBuilderMethod() {
        // When
        final Book book = Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build();

        // Then
        assertThat(book, Matchers.notNullValue());
    }

    @Test
    public void shouldPassEqualityTests() {
        // Given
        final Book bookA = Book.builder().author("Seth Kellas").title("Teaching Tech Leads")
                .description("Lessons learned and constant reminders on how to be a better technical lead.")
                .coverImageUrl("https://teachingtechleads.com/wp-content/uploads/2019/07/notebook-1280538-1024x681.jpg")
                .isbn("1234567890123").build();

        // When
        final Book bookB = new Book(bookA.getId(), bookA.getAuthor(), bookA.getTitle(), bookA.getDescription(),
                bookA.getIsbn(), bookA.getSynopsis(), bookA.getCoverImageUrl());

        // Then
        assertThat(bookA, Matchers.equalToObject(bookB));

    }

    @Test
    public void shouldTestForNullAuthor() throws Exception {
        // Given
        exception.expect(NullPointerException.class);
        exception.expectMessage("author is marked non-null but is null");

        // When
        Book.builder().title("Teaching Tech Leads").build();
    }

    @Test
    public void shouldTestForNullTitle() throws Exception {
        // Given
        exception.expect(NullPointerException.class);
        exception.expectMessage("title is marked non-null but is null");

        // When
        Book.builder().author("Seth Kellas").build();
    }

}
