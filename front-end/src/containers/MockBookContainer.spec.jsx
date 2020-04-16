import React from 'react';
import { render, waitFor } from '@testing-library/react';

import MockBookContainer from './MockBookContainer';
import { getBooks } from '../services/MockBookService';

describe('MockBookContainer functional component', () => {
    it('renders with mocked book service provider', async () => {
        // Given
        const books = getBooks();

        // When
        const { getAllByText, getByText } = render(<MockBookContainer />);

        await waitFor(() => {
            // Then
            expect(getByText(books[0].author));
            expect(getAllByText(books[0].title));
            expect(getByText(books[0].description));
        });
    });
});
