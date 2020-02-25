import React, { useState, useEffect } from 'react';
import BookCard from './BookCard';

export default () => {
    const [books, setBooks] = useState([]);
    const [loaded, setLoaded] = useState(false);
    useEffect(() => {
        if (!loaded) {
            const findBooks = async () => {
                const { books: response } = await fetch('/books')
                    .then(data => data.json())
                    .then(json => resolveBooks(json));
                setBooks(response);
            };
            findBooks();
        }
        setLoaded(true);
    }, [loaded]);
    const resolveBooks = jsonObject => {
        // TODO
    };
    return (
        <div id="app">
            {books.length > 0 ? (
                books.map((book, key) => {
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
                <span>Loading</span>
            )}
        </div>
    );
};
