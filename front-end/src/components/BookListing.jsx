import React from 'react';
import BookCard from './BookCard';

export default data => {
    return (
        <React.Fragment>
            {data.books.length > 0 ? (
                data.books.map((book, key) => {
                    return (
                        <BookCard
                            key={key}
                            title={book.title}
                            author={book.author}
                            description={book.description}
                        />
                    );
                })
            ) : (
                <span>No Books Provided</span>
            )}
        </React.Fragment>
    );
};
