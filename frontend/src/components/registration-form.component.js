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

function RegistrationForm() {
        let navigate = useNavigate();
    
        const [name, setName] = useState("");
        const [login, setLogin] = useState("");
        const [email, setEmail] = useState("");
        const [password, setPassword] = useState("");
        const [errorMessage, setErrorMessage] = useState("");
        const [loading, setLoading] = useState(false);

        const handleRegistration = function (values) {
            AuthService.register(values.name, values.login, values.email, values.password).then(
                () => {
                    navigate("/login");
                    setErrorMessage("");
                    // window.location.reload();
                },
                (error) => {
                    setErrorMessage(
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString()
                        )
                    setLoading(false);
                }
            );
        };
        return (
            <Formik
                initialValues={{name: '', login: '', email: '', password: ''}}
                validationSchema={Yup.object({
                    name: Yup.string()
                        .min(3, 'Name is too short - should be 3 chars minimum.')
                        .max(20, 'Name is too long - should be 20 chars maximum.')
                        .matches(/^[A-Za-z0-9]+$/, 'Name can only contain Latin letters and digital numbers.')
                        .required('Required'), 

                    login: Yup.string()
                        .min(3, 'Login is too short - should be 3 chars minimum.')
                        .max(20, 'Login is too long - should be 20 chars maximum.')
                        .matches(/^[A-Za-z0-9]+$/, 'Login can only contain Latin letters and digital numbers.')
                        .required('Required'), 

                    email: Yup.string().email('Invalid email.').required('Required'), 

                    password: Yup.string()
                        .min(6, 'Password is too short - should be 6 chars minimum.')
                        .max(50, 'Password is too long - should be 20 chars maximum.')
                        .matches(/^[A-Za-z0-9]+$/, 'Password can only contain Latin letters and digital numbers.')
                        .required('Required'), 

                    confirmPassword: Yup.string()
                        .required("Please confirm your password")
                        .oneOf([Yup.ref("password")], "Passwords do not match"),
                })}

                onSubmit={(values, {setSubmitting}) => {
                    setLoading(true);
                    setTimeout(() => handleRegistration(values), 400);
                    setSubmitting(false);
                }}
            >
                <Form>
                    <Col>
                        <Row>
                            <label htmlFor="name">Name</label>
                            <Field name="name" type="text"/>
                        </Row>
                        <ErrorMessage name="name"/>
                    </Col>

                    <Col>
                        <Row>
                            <label htmlFor="login">Login</label>
                            <Field name="login" type="text"/>
                        </Row>
                        <ErrorMessage name="login"/>
                    </Col>

                    <Col>
                        <Row>
                            <label htmlFor="email">Email</label>
                            <Field name="email" type="email"/>
                        </Row>
                        <ErrorMessage name="email"/>
                    </Col>
    
                    <Col>
                        <Row>
                            <label htmlFor="password">Password</label>
                            <Field name="password" type="password"/>
                        </Row>
                        <ErrorMessage name="password"/>
                    </Col>

                    <Col>
                        <Row>
                            <label htmlFor="confirmPassword">Confirm password</label>
                            <Field name="confirmPassword" type="password"/>
                        </Row>
                        <ErrorMessage name="confirmPassword"/>
                    </Col>
    
                    <button className="btn btn-light btn-block" type="submit" disabled={loading}>
                        {loading && (
                            <span className="spinner-border spinner-border-sm"></span>
                        )}
                        <span>Sign up</span>
                    </button>

                    { errorMessage &&
                    <h3 className="error"> { errorMessage } </h3> 
                    }
                </Form>
            </Formik>
        );
    }
    
    export default RegistrationForm;