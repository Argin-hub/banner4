import React, {Component} from 'react';
import {Card, Table, Image, ButtonGroup, Button } from "react-bootstrap";
import {Link} from "react-router-dom";
import axios from "axios";

export default class BookList extends Component{

    constructor(props){
            super(props);
            this.state = {
                books : []
            };
    }

    componentDidMount(){
        this.findAllBooks();
    }

    findAllBooks(){
        axios.get("http://localhost:8080/banner")
            .then(response => response.data)
            .then((data) => {
                this.setState({books: data});
            });
    };

    deleteBook = (bookId) => {
axios.delete("http://localhost:8080/banner/" + bookId)
                .then(response => {
                    if(response.data != null){
                        alert("Bokk deleted successfully!");
                        this.setState({
                            books: this.state.books.filter(book => book.id !== bookId)
                        });
                    }
                })
    };

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>News List</Card.Header> 
                <Card.Body>
                    <Table>
                    <thead>
    <tr>
      <th>Title</th>
      <th>Text</th>
      <th>Price</th>
      <th>Category</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
  {this.state.books.length === 0 ? 
  <tr align = "center">
      <td colSpan="6">banners avalibable </td>
      </tr> : 
      this.state.books.map((book) => (
            <tr key={book.id}>
                <td>{book.name} </td>
                <td>{book.text} </td>
                <td>{book.price} </td>
                <td>
                    {book.creationDate} 
                    </td>
                
                <td>
                    <ButtonGroup>
                    <Link to={"edit/"+book.id} className="navbar-link"> edit </Link>
    
                        <Button onClick={this.deleteBook.bind(this, book.id)}>delete </Button>{''}
                    </ButtonGroup>
                </td>
             </tr>
      ))
          }
  </tbody>
                        </Table>
                    </Card.Body>
                </Card>
        );
    }
}
