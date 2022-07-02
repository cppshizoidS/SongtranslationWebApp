import axios from "axios";
const API_URL = "http://localhost:8080/api/auth/";

const register = (name, login, email, password) => {
    return axios.post(API_URL + "signup", {
        name,
        login,
        email,
        password
    });
};

const logIn = (login, password) => {
    return axios
        .post(API_URL + "signin", {
            login,
            password,
        })
        .then((response) => {
            if (response.data.token) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }
            return response.data;
        });
};

const logOut = () => {  
    localStorage.removeItem("user");
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
    register,
    logIn,
    logOut,
    getCurrentUser,
};
export default AuthService;