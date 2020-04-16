import React from 'react';

import 'materialize-css/dist/css/materialize.css';
import { Row, Col, Container } from 'react-materialize';

import MockBookContainer from './containers/MockBookContainer';

const App = () => {
    return (
        <Container>
            <Row>
                <Col m={6} s={12}>
                    <MockBookContainer />
                </Col>
            </Row>
        </Container>
    );
};

export default App;
