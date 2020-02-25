import React from 'react';
import {
    render,
    waitForElement,
    waitForElementToBeRemoved,
} from '@testing-library/react';
import { FetchMock, fetchMock } from '@react-mock/fetch';
import { act } from 'react-dom/test-utils';

import BookListing from './BookListing';

describe('BookListing functional component', () => {
    beforeAll(() => {
        global.fetch = fetch;
    });
    afterAll(() => {
        fetchMock.restore();
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

        await act(async () => {
            // When
            const { getByText } = renderComponentWithResponse([book]);

            // Then
            await waitForElement(() => getByText('test title'));
        });
    });

    const renderComponentWithResponse = response => {
        return render(
            <FetchMock
                mocks={[
                    {
                        matcher: '/books',
                        method: 'GET',
                        response: { books: response },
                    },
                ]}
            >
                <BookListing />
            </FetchMock>,
        );
    };
});
