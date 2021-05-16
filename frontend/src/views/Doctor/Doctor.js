import React, {useState} from "react";
import Navbar from "react-bootstrap/Navbar";
import "./Doctor.css";
import Routes from "../../router/Routes";
import Nav from "react-bootstrap/Nav";
import {LinkContainer} from "react-router-bootstrap";
import {DarkModeSwitch} from 'react-toggle-dark-mode';
import {NavDropdown} from "react-bootstrap";
import "react-custom-flag-select/lib/react-custom-flag-select.min.css";


function Patient() {
    const [isDarkMode, setIsDarkMode] = useState(() => false);
    const urlPL = "https://img.icons8.com/color/96/000000/poland-circular.png"
    const urlEN = "https://img.icons8.com/color/48/000000/great-britain-circular.png"
    const [language, setLanguage] = useState(() => "PL");
    const [flag, setFlag] = useState(() => urlEN);

    function handleOnClick() {
        if (language === "EN") {
            setPL()
        } else {
            setEN()
        }
    }

    function setEN() {
        setLanguage("EN");
        setFlag(urlPL);
    }

    function setPL() {
        setLanguage("PL");
        setFlag(urlEN);
    }

    return (
        <div className="App container py-3 ">
            <Navbar collapseOnSelect bg="light" expand="md" className="shadow-box-example mb-3">
                <LinkContainer to="/">
                    <Navbar.Brand className="font-weight-bold text-muted">
                        Home
                    </Navbar.Brand>
                </LinkContainer>
                <Navbar.Toggle/>
                <Navbar.Collapse className="justify-content-end">
                    <Nav activeKey={window.location.pathname}>
                        <NavDropdown title="Appointments" id="navbarScrollingDropdown">
                            <NavDropdown.Item>
                                <LinkContainer to="/my-appointments">
                                    <Nav.Link>My appointments</Nav.Link>
                                </LinkContainer>
                            </NavDropdown.Item>
                            <NavDropdown.Divider/>
                            <NavDropdown.Item>
                                <LinkContainer to="/list-doctors">
                                    <Nav.Link>List of doctors</Nav.Link>
                                </LinkContainer>
                            </NavDropdown.Item>
                        </NavDropdown>
                        <LinkContainer to="/prescriptions">
                            <Nav.Link>Prescriptions</Nav.Link>
                        </LinkContainer>
                        <LinkContainer to="/dashboard">
                            <Nav.Link>Documentation</Nav.Link>
                        </LinkContainer>
                        <LinkContainer to="/account">
                            <Nav.Link>My Account</Nav.Link>
                        </LinkContainer>
                    </Nav>
                    <DarkModeSwitch
                        style={{marginLeft: '1rem'}}
                        checked={isDarkMode}
                        onChange={setIsDarkMode}
                        size={30}
                        sunColor={"#FFDF37"}
                        moonColor={"#bfbfbb"}
                    />
                    <img onClick={handleOnClick} style={{marginLeft: "10px", maxWidth:"30px"}} src={flag} alt="Logo" />

                </Navbar.Collapse>
            </Navbar>
            <Routes/>
        </div>
    );
}

export default Patient;