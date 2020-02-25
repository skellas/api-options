import React from 'react';
import { Card } from 'react-materialize';

const BookCard = ({ title, author, description }) => {
    return (
        <Card
            title={title}
            className="blue-grey darken-1"
            textClassName="white-text"
        >
            <p>
                <span className="label">Author: </span>
                <span className="value" id="author">
                    {author}
                </span>
            </p>
            <p>
                <span className="label">Description: </span>
                <span className="value" id="description">
                    {description}
                </span>
            </p>
        </Card>
    );
};
export default BookCard;
