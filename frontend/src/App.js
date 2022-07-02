import React, { Component, useEffect, useState } from "react";
import { Switch, Route, Link, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import SongsList from "./components/songs-list.component";
import RegistrationForm from "./components/registration-form.component";
import LoginForm from "./components/login-form.component";
import Home from "./components/home.component.js"
import AuthService from "./services/auth.service.js"
import Profile from "./components/profile.component.js"


function App() {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      setCurrentUser(user);
    }
  }, []);

  const logout = () => {
    AuthService.logOut();
  };

    return (
      <div>
        <nav className="navbar navbar-expand">
          <a href="/home" className="navbar-brand">
            SoTra
          </a>
          <div className="navbar-nav">

            <li className="nav-item">
              <a class="nav-link" href="/home">
                Home
              </a>
            </li>

            <li className="nav-item">
              <a class="nav-link" href="/search">
                Search songs
              </a>
            </li>

          </div>
          <div className="navbar-nav mr-auto">
            {currentUser ? (
              <div className="navbar-nav mr-auto">
                <li className="nav-item">image.png
                  <Link to={"/profile"} className="nav-link">
                    {currentUser.name}
                  </Link>
                </li>
                <li className="nav-item">
                  <a href="/login" className="nav-link" onClick={logout}>
                    Log out
                  </a>
                </li>
              </div>
            ) : (
              <div className="navbar-nav mr-auto">
                <li className="nav-item">
                  <Link to={"/login"} className="nav-link">
                    Sign In
                  </Link>
                </li>
                <li className="nav-item">
                  <Link to={"/registration"} className="nav-link">
                    Sign Up
                  </Link>
                </li>
              </div>
            )}
          </div>
        </nav>

        <div className="container mt-3">
          <Routes>
            <Route index element={<Home />} />
            <Route path="/home" element={<Home />} />
            <Route path="/registration" element={<RegistrationForm />} />
            <Route path="/login" element={<LoginForm />} />
            <Route path="/search" element={<SongsList />} />
            <Route path="/profile" element={<Profile />} />
          </Routes>
        </div>
      </div>
    );
  }
export default App;