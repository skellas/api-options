import React from 'react';
import { render, waitFor } from '@testing-library/react';

import BookContainer from './BookContainer';
import { getBooks } from '../services/MockBookService';

describe('BookContainer functional component', () => {
    it('renders with provided book lookup', async () => {
        // Given
        const books = getBooks();

        // When
        const { getAllByText, getByText } = render(<BookContainer bookLookup={() => getBooks()} />);

        await waitFor(() => {
            // Then
            expect(getByText(books[0].author));
            expect(getAllByText(books[0].title));
            expect(getByText(books[0].description));
        });
    });

    it('will render no books loaded when no lookup provided', async () => {
        // When
        const { getByText } = render(<BookContainer />);

        // Then
        await waitFor(() => {
            // Then
            expect(getByText('No Books Provided'));
        });
    });
});
