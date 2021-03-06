import React from 'react';
import { render, waitFor } from '@testing-library/react';

import { getBooks } from '../services/MockBookService';
import BookListing from './BookListing';

describe('BookListing functional component', () => {
    it('renders with no books', () => {
        // When
        const { getByText } = render(<BookListing books={[]} />);

        // Then
        getByText('No Books Provided');
    });

    it('renders books passed in', async () => {
        // Given
        const books = getBooks();

        // When
        const { getAllByText, getByText } = render(
            <BookListing books={books} />,
        );

        await waitFor(() => {
            // Then
            expect(getByText(books[0].author));
            expect(getAllByText(books[0].title));
            expect(getByText(books[0].description));
        });
    });
});
