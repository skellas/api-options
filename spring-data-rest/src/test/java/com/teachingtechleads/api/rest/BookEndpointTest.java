package com.teachingtechleads.api.rest;

import static java.lang.String.format;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachingtechleads.data.object.Book;
import com.teachingtechleads.data.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookEndpointTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldCreateOnPost() throws Exception {
        // Given
        final Book book = Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build();

        // When
        final MvcResult response = mvc
                .perform(post("/books").accept(APPLICATION_JSON).content(mapper.writeValueAsBytes(book)))
                .andExpect(status().is(CREATED.value())).andReturn();

        // Then
        final String locationUrl = response.getResponse().getHeader("Location");
        final Long id = Long.valueOf(locationUrl.substring(locationUrl.lastIndexOf("/") + 1));
        final Book persistedBook = bookRepository.findById(id).get();

        assertThat(persistedBook.getAuthor(), equalTo(book.getAuthor()));
        assertThat(persistedBook.getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldGetById() throws Exception {
        // Given
        final Book book = bookRepository
                .save(Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build());

        // When
        final MvcResult response = mvc.perform(get(format("/books/%d", book.getId())).accept(APPLICATION_JSON))
                .andExpect(status().is(OK.value())).andReturn();

        // Then
        assertThat(response.getResponse().getContentAsString(), containsString(book.getAuthor()));
        assertThat(response.getResponse().getContentAsString(), containsString(book.getTitle()));
    }

    @Test
    public void shouldThrowErrorMessage() throws Exception {
        // Given
        final String book = "{\"title\":\"Teaching Tech Leads\"}";

        // When
        final MvcResult response = mvc.perform(post("/books").accept(APPLICATION_JSON).content(book))
                .andExpect(status().is(CONFLICT.value())).andReturn();

        // Then
        final String responseBody = response.getResponse().getContentAsString();
        assertThat(responseBody, containsString("NULL not allowed for column \\\"AUTHOR\\\""));

    }

}
