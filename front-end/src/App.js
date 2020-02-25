import React from 'react';
import BookListing from './components/BookListing';

import 'materialize-css/dist/css/materialize.css';
import { Row, Col, Container } from 'react-materialize';

const App = () => {
    return (
        <Container>
            <Row>
                <Col 
                    m={6}
                    s={12}
                >
                    <BookListing />
                </Col>
            </Row>
        </Container>
    );
};

export default App;
