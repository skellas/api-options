import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import fetch from 'jest-fetch-mock';

import BookListing from './BookListing';

describe('BookListing functional component', () => {
    beforeEach(() => {
        fetch.resetMocks();
    });

    it('renders loading screen', () => {
        // When
        const { getByText } = render(<BookListing />);

        // Then
        getByText('Loading');
    });

    it('renders fetched data', async () => {
        // Given
        const book = {
            title: 'test title',
            author: 'test author',
            description: 'description one',
        };
        fetch.mockResponse(JSON.stringify({ books: [book] }));

        // When
        render(<BookListing />);
        await waitFor(() => {
            // Then
            const titleEls = screen.getAllByText('test title');
            expect(titleEls).toHaveLength(2);
        });
    });
});
