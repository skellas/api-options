import React from 'react';

const BookCard = ({ title, author, description }) => {
    return (
        <div className="bookCard">
            <p>
                <span className="label">Title:</span>
                <span className="value" id="title">
                    {title}
                </span>
            </p>
            <p>
                <span className="label">Author:</span>
                <span className="value" id="author">
                    {author}
                </span>
            </p>
            <p>
                <span className="label">Description:</span>
                <span className="value" id="description">
                    {description}
                </span>
            </p>
        </div>
    );
};
export default BookCard;
