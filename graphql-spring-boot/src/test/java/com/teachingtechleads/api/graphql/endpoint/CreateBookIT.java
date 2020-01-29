package com.teachingtechleads.api.graphql.endpoint;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.teachingtechleads.api.graphql.resolver.BookResolver;
import com.teachingtechleads.api.graphql.type.BookInput;
import com.teachingtechleads.data.object.Book;
import com.teachingtechleads.data.repository.BookRepository;

@RunWith(SpringRunner.class)
@GraphQLTest
@Import({ BookResolver.class })
public class CreateBookIT {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void shouldCreateBook() throws Exception {
        // Given
        final Book book = Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build();
        BDDMockito.given(bookRepository.save(book)).willReturn(book.toBuilder().id(1L).build());

        // When
        final BookInput input = BookInput.builder().author("Seth Kellas").title("Teaching Tech Leads").build();
        final ObjectNode variables = mapper.createObjectNode();
        variables.set("book", mapper.valueToTree(input));
        final GraphQLResponse response = graphQLTestTemplate.perform("create-book.graphql", variables);

        // Then
        assertThat(response.get("$.data.createBook.author"), equalTo("Seth Kellas"));
        assertThat(response.get("$.data.createBook.title"), equalTo("Teaching Tech Leads"));

    }
}
