import {useNavigate} from "react-router-dom";
import React, {useRef, useState} from "react";
import {Formik, Field, Form, ErrorMessage} from "formik";
import * as Yup from 'yup';
import {Col, Row} from "react-bootstrap";
import AuthService from "../services/auth.service";

export const successLogin = (username, password) => {
    return new Promise(function(resolve, reject) {
        resolve({});
    });
}

function LoginForm() {

    let navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    const handleLogin = function (values) {
        AuthService.logIn(values.login, values.password).then( // AuthService.login
            () => {
                setErrorMessage("");
                navigate("/profile");
                window.location.reload();
            },
            (error) => {
                setErrorMessage (
                    "Incorrect login or password. Try again."
                );
                setLoading(false);
            }
        );
    };
    return (
        <Formik
            initialValues={{login: '', password: ''}}
            validationSchema={Yup.object({
                login: Yup.string()
                    .min(3, 'Login is too short - should be 3 chars minimum.')
                    .max(20, 'Login is too long - should be 20 chars maximum.')
                    .matches(/^[A-Za-z0-9]+$/, 'Login can only contain Latin letters and digital numbers.')
                    .required('Required'), 
                password: Yup.string()
                    .min(6, 'Password is too short - should be 6 chars minimum.')
                    .max(20, 'Password is too long - should be 20 chars maximum.')
                    .matches(/^[A-Za-z0-9]+$/, 'Password can only contain Latin letters and digital numbers.')
                    .required('Required')
                    
            })}
            onSubmit={(values, {setSubmitting}) => {
                setLoading(true);
                setTimeout(() => handleLogin(values), 400);
                setSubmitting(false);
            }}
        >
            <Form>
                <Col>
                    <Row>
                        <label htmlFor="login">Login</label>
                        <Field name="login" type="text"/>
                    </Row>
                    <ErrorMessage name="login"/>
                </Col>

                <Col>
                    <Row>
                        <label htmlFor="password">Password</label>
                        <Field name="password" type="password"/>
                    </Row>
                    <ErrorMessage name="password"/>
                </Col>

                <button className="btn btn-light btn-block" type="submit" disabled={loading}>
                    {loading && (
                        <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Sign in</span>
                </button>

                { errorMessage &&
                    <h3 className="error"> { errorMessage } </h3> 
                }
            </Form>
        </Formik>
    );
}

export default LoginForm;