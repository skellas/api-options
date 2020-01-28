package com.teachingtechleads.api.graphql.endpoint;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
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
public class FindAllIT {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private BookRepository bookRepository;

    @SuppressWarnings("unchecked")
    @Test
    public void shouldFindAllAvailableBooks() throws Exception {
        // Given
        BDDMockito.given(bookRepository.findAll())
                .willReturn(asList(Book.builder().author("Seth Kellas").title("Teaching Tech Leads").build(),
                        Book.builder().author("Pat Kua").title("Talking with Tech Leads").build()));

        // When
        final GraphQLResponse response = graphQLTestTemplate.postForResource("find-all.graphql");

        // Then
        assertThat((List<Book>) response.get("$.data.books", List.class), Matchers.hasSize(2));
    }
}
