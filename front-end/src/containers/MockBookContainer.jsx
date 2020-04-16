import React, { useEffect, useState } from 'react';
import { getBooks } from '../services/MockBookService';
import BookListing from '../components/BookListing';

function MockBookContainer() {
    const [data, setData] = useState([]);
    const [loaded, setLoaded] = useState(false);
    useEffect(() => {
        if (!loaded) {
            setData(getBooks());
            setLoaded(!loaded);
        }
    }, [loaded]);
    return <BookListing books={data} />;
}

export default MockBookContainer;
