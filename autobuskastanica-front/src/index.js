import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Link, HashRouter as Router, Switch } from 'react-router-dom';
import {Container, Button, Navbar, Nav} from "react-bootstrap"
import Login from "./components/login/Login"
import Home from './components/Home';

import NotFound from './components/NotFound';
import {logout} from './services/auth'
import Linije from './components/linije/Linije';
import AddLinije from './components/linije/AddLinije';
import EditLinije from './components/linije/EditLinije';
import Rezervacija from './components/linije/Rezervacija';

class App extends React.Component {

    render(){
        return (
            <div>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">Home</Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/linije">Linije</Nav.Link>
                    
                    {window.localStorage['jwt'] ? 
                            <Button onClick = {()=>logout()}>Logout</Button> :
                            <Nav.Link as={Link} to="/login">Log in</Nav.Link>
                    }
                    </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"25px"}}>
                    <Switch>
                        <Route exact path="/" component={Home} />
                        <Route exact path="/linije" component={Linije} />
                        <Route exact path="/linije/add" component={AddLinije} />
                        <Route exact path="/linije/edit/:id" component={EditLinije} />
                        <Route exact path="/linije/rezervacija/:id" component={Rezervacija} />
                        <Route exact path="/login" component={Login}/>
                        <Route component={NotFound} />
                    </Switch>
                    </Container>

                </Router>
            </div>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.querySelector('#root')
);