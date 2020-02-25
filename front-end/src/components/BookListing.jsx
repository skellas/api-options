import React, { useState, useEffect } from 'react';
import BookCard from './BookCard';

export default () => {
    const [books, setBooks] = useState([]);
    const [loaded, setLoaded] = useState(false);
    useEffect(() => {
        if (!loaded) {
            const findBooks = async () => {
                const response = await fetch('http://localhost:8080/books')
                    .then(data => data.json())
                    .then(json => resolveBooks(json))
                    .catch(err => console.log(err));
                response && setBooks(response.books);
            };
            findBooks();
        }
        setLoaded(true);
    }, [loaded]);
    const resolveBooks = jsonObject => {
        // TODO
        return jsonObject;
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
