import axios from "axios"

export default class UserService {
    constructor() {
        this.axios = axios.create({
            baseURL: "http://localhost:8080"
        })
    }

    /* Função que enviará as credenciais de LOGIN para o BACKEND e se caso exista o usuário cadastrado
    armazena no localStorage as credenciais*/
    async login (userData) {
        const { data } = await this.axios.post('/login', userData);

        if (data.status === true) {
            localStorage.setItem("id", data.user.id);
            localStorage.setItem("name", data.user.name);
            localStorage.setItem("email", data.user.email);
            alert(data.message);
            return data.status;
        }
        else {
            alert(data.message);
        }
    }

    /* Função para registrar um novo usuário na plataforma */
    async resgister (userData){
        return this.axios.post('/user', userData);
    }

    /* Função verificar se existe um usuário logado na plataforma */
    authenticatedUser() {
        // eslint-disable-next-line eqeqeq
        return localStorage.getItem("email") != undefined ? true : false;
    }

}