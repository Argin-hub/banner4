import logo from './logo.svg';
import './App.css';
import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import {Container, Row, Jumbotron, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Book from './components/Book';
import BookList from './components/BookList';




function App() {
     const marginTop = {
marginTop: "20px"
     };
  return (
    <Router>

      <NavigationBar />
      <Container>
        <Row>
              <Col lg = {12} style = {marginTop}>

              <Switch>
                    <Route path="/" exact component={Welcome} />
                    <Route path="/add" exact component={Book} />
                    <Route path="/edit/:id" exact component={Book} />
                    <Route path="/list" exact component={BookList} />
                </Switch>

               
</Col>
          </Row>
      
        </Container>
    </Router>
  );
}

export default App;
