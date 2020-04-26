import React, { useEffect, useState } from 'react';
import BookListing from '../components/BookListing';

function BookContainer({bookLookup}) {
    const [data, setData] = useState([]);
    const [loaded, setLoaded] = useState(false);
    useEffect(() => {
        if (!loaded && bookLookup) {
            setData((bookLookup()));
            setLoaded(!loaded);
        }
    }, [loaded]);
    return <BookListing books={data} />;
}

export default BookContainer;
