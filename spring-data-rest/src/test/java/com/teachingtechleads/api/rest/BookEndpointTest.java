package com.teachingtechleads.api.rest;

import static java.lang.String.format;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Test
    public void shouldGetById() throws Exception {
        // Given
        final Book book = bookRepository
                .save(Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build());

        // When
        final MvcResult response = mvc
                .perform(MockMvcRequestBuilders.get(format("/books/%d", book.getId())).accept(APPLICATION_JSON))
                .andExpect(status().is(OK.value())).andReturn();

        // Then
        assertThat(response.getResponse().getContentAsString(), containsString(book.getAuthor()));
        assertThat(response.getResponse().getContentAsString(), containsString(book.getTitle()));
    }

}
