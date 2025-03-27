import { Container , Nav, Navbar, Button } from 'react-bootstrap';
import { Outlet } from 'react-router-dom';
import { useState } from 'react';

function Layout() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [expanded, setExpanded] = useState(false);

    return <>
        <div>
        <Navbar expand="sm" expanded={expanded} onToggle={(val) => setExpanded(val)} bg="dark" data-bs-theme="dark">
            <Container>
                <Navbar.Brand href="/home">Navbar</Navbar.Brand>

                <Navbar.Toggle aria-controls="main-navbar" />
                <Navbar.Collapse id="main-navbar">
                
                <Nav className="me-auto d-flex">
                    <Nav.Link href="/home">Home</Nav.Link>
                    <Nav.Link href="/profile">Profile</Nav.Link>
                    <Nav.Link href="/view">View</Nav.Link>
                </Nav>

                <Nav className="ms-auto">
                    {isLoggedIn ? 
                    <>
                    <Nav.Link href="logout">Logout</Nav.Link>
                    <Navbar.Text style={{fontSize: "10px", marginTop:6}}>
                        Signed in as: Minsuk You
                    </Navbar.Text>
                    
                    </>
                    :
                    <>
                    <Nav.Link href="/login">Login</Nav.Link>
                    <Nav.Link href="/register">Register</Nav.Link>
                    </>
                    }
                </Nav>
                </Navbar.Collapse>

                

            </Container>
        </Navbar>

        <div style={{ marginTop: 20 }}>
            <Container>
                <Outlet />
            </Container>
        </div>
        </div>
    </>
}

export default Layout;