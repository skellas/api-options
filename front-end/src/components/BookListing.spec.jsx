import React from 'react';
import {
    render,
    waitForElement,
} from '@testing-library/react';
import fetch from 'jest-fetch-mock';
import { act } from 'react-dom/test-utils';

import BookListing from './BookListing';

describe('BookListing functional component', () => {
    beforeEach(() => {
      fetch.resetMocks()
    })

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
            description: 'description one'
        };
        fetch.mockResponse(JSON.stringify({books: [book]}));

        await act(async () => {
            // When
            const { getByText } = render(<BookListing />);

            // Then
            await waitForElement(() => getByText('test title'));
        });
    });

});
