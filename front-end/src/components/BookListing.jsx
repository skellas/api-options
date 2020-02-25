import React, { useState, useEffect } from 'react';
import BookCard from './BookCard';

export default () => {
    const [books, setBooks] = useState([]);
    const [loaded, setLoaded] = useState(false);
    useEffect(() => {
        if (!loaded) {
            const findBooks = async () => {
                // const response = await fetch('http://localhost:8080/books')
                //     .then(data => data.json())
                //     .then(json => resolveBooks(json))
                //     .catch(err => console.log(err));
                // response && setBooks(response.books);
                setBooks([
                    {
                        title: 'Talking with Tech Leads',
                        author: 'Pat Kua',
                        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ultricies, odio efficitur vehicula scelerisque, erat tortor facilisis ligula, eu aliquet enim sapien sit amet dolor.'
                    },
                    {
                        title: 'Manager\'s Path',
                        author: 'Camille Fournier',
                        description: 'Sed vulputate elit ex, sed rutrum enim tristique a. Phasellus faucibus fermentum lectus. Nunc vestibulum arcu ut nulla fermentum posuere.'
                    },
                ])
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
        <React.Fragment>
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
        </React.Fragment>
    );
};
