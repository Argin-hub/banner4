import React from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import {Link} from "react-router-dom";

class NavigationBar extends React.Component{
    render() {
        return(
        <Navbar bg="dark" variant="dark">
            <Link to={""} className="navbar-brand">
                       <img width="25" src="https://habrastorage.org/getpro/habr/post_images/68b/835/f86/68b835f86d9402568aa41a2946798246.png"></img>
               <p> main page </p>
                </Link>

        <Nav className="mr-auto">
      <Link to={"add"} className="navbar-link"> Add news </Link>
      <Link to={"list"} className="navbar-link"> All news </Link>
   
    </Nav>
            </Navbar>
        );
    }
}

export default NavigationBar;