import React, {Component} from 'react';
import {Card, Form, Button, Label, Col} from "react-bootstrap";

import axios from "axios";

export default class Book extends Component{
    constructor(props){
super(props);
this.state = this.initialState;
this.bookChange = this.bookChange.bind(this);
this.submitBook = this.submitBook.bind(this);
}

initialState = {
    id: '', name:'', text:'', price:'',creationDate:''
};

componentDidMount(){
    const bookId = +this.props.match.params.id;
    if(bookId) {
        this.findBookById(bookId);
    }
}

findBookById = (bookId) => {
    axios.get("http://localhost:8080/banner/"+bookId)
            .then(response => {
                if(response.data != null) {
                        this.setState({
                            id: response.data.id,
                            text: response.data.text,
                            name: response.data.name,
                            price: response.data.price
                        });
                }
            }).catch((error) => {
                console.error("Eror "+error);
            });
}

resetBook = () => {
    this.setState(() =>this.initialState);
}

submitBook = event => {
 alert('Name banner ' +this.state.name + 'Text '+this.state.text+ 'Price '+ this.state.price);
event.preventDefault();

const book = {
    name: this.state.name,
     text: this.state.text,
    price: this.state.price,
    creationDate:this.state.creationDate
};

axios.post("http://localhost:8080/banner", book)
    .then(response => {
        if(response.data != null) {
            this.setState(this.initialState);
            alert("News saved successfully");
        }
    });
    

};

updateBook = event => {

    event.preventDefault();

const book = {
    id: this.state.id,
    name: this.state.name,
     text: this.state.text,
    price: this.state.price,
    creationDate:this.state.creationDate
};

axios.put("http://localhost:8080/banner", book)
    .then(response => {
        if(response.data != null) {
            this.setState(this.initialState);
            alert("News saved successfully");
        } 
    });
    

};








bookChange = event =>{
        this.setState({
            [event.target.name]:event.target.value
        });
}
    render() {
        const {name, text, price} = this.state;
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>Add News</Card.Header> 

               
                <Form onReset={this.resetBook} onSubmit={this.submitBook} id="bookFormId">
               
                    <Form.Row>

               <Form.Group as={Col} controlId="formGridTitle">
                   <Form.Label> Tittle </Form.Label>
                   <Form.Control required autoComplete="off"
                    type="test" name="name" 
                    value={name}
                    onChange={this.bookChange}
                   className={"bg-dark-white"} 
                   placeholder="Enter Banner Title" />
                </Form.Group>

                <Form.Group as={Col} controlId="formGridText">
                   <Form.Label> Text </Form.Label>
                   <Form.Control required autoComplete="off"
                   type="test" name="text" 
                   value={text}
                   onChange={this.bookChange}
                    className={"bg-dark-white"} 
                   placeholder="Enter Banner text" />
                </Form.Group>

                <Form.Group as={Col} controlId="formGridPrice">
                   <Form.Label> price </Form.Label>
                   <Form.Control required autoComplete="off" 
                   type="test" name="price" 
                   value={price}
                   onChange={this.bookChange}
                   className={"bg-dark-white"} 
                   placeholder="Enter Banner price" />
                </Form.Group>

                <Form.Group as={Col} controlId="formGridCategory">
                   <Form.Label> category </Form.Label>
                   <Form.Control required type="test" name="category" 
                   value={this.state.price}
                   onChange={this.bookChange}
                   className={"bg-dark-white"} 
                   placeholder="Enter Banner category" />
                </Form.Group>
    </Form.Row>
    <Button variant="primary" type="submit">
    Submit
  </Button>

  <Button variant="primary" type="reset">
    Reset
  </Button>

</Form>
                </Card>
        );
    }
}

