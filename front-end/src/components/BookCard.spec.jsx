import React from 'react';
import { render } from '@testing-library/react';
import BookCard from './BookCard';

test('renders title', () => {
  // Given
  const { getByText } = render(<BookCard title='test title' />);

  // When
  const titleEl = getByText('test title');

  // Then
  expect(titleEl).toBeInTheDocument();
});
test('renders author', () => {
  // Given
  const { getByText } = render(<BookCard author='test author' />);

  // When
  const authorEl = getByText('test author');
  expect(authorEl).toBeInTheDocument();
});
test('renders description', () => {
  // Given
  const { getByText } = render(<BookCard title='test description' />);

  // When
  const descriptionEl = getByText('test description');

  // Then
  expect(descriptionEl).toBeInTheDocument();
});
