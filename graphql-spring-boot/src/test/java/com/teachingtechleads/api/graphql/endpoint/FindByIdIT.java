package com.teachingtechleads.api.graphql.endpoint;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.teachingtechleads.api.graphql.resolver.BookResolver;
import com.teachingtechleads.data.object.Book;
import com.teachingtechleads.data.repository.BookRepository;

@RunWith(SpringRunner.class)
@GraphQLTest
@Import({ BookResolver.class })
public class FindByIdIT {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void shouldReturnBookById() throws Exception {
        // Given
        BDDMockito.given(bookRepository.findById(1L))
                .willReturn(Optional.of(Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build()));

        // When
        final GraphQLResponse response = graphQLTestTemplate.postForResource("find-by-id-1.graphql");

        // Then
        assertThat(response.get("$.data.book.author"), equalTo("Seth Kellas"));
        assertThat(response.get("$.data.book.title"), equalTo("Teaching Tech Leads"));

    }

    @Test
    public void shouldReturnNull() throws Exception {
        // Given
        BDDMockito.given(bookRepository.findById(1L)).willReturn(Optional.empty());

        // When
        final GraphQLResponse response = graphQLTestTemplate.postForResource("find-by-id-1.graphql");

        // Then
        assertThat(response.get("$.data.book"), equalTo(null));

    }

}
