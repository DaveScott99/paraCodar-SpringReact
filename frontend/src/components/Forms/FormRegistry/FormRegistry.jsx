import React, { useState } from "react";
import { validateConfirmPassword, validateEmail, validateFirstName, validateLastName, validatePassword, validateUsername } from "../../../utils/Validators";
import Input from "../../Generics/Input/Input";
import { resgisterUser } from "../../../services/Api";
import { useNavigate } from "react-router";
import { Button } from "../../Generics/Button/Button";
import { Form } from "./FormRegistryStyles";

export default function FormRegistry({ className }) {
    
    const navigate = useNavigate();

    const [loading, setLoading] = useState();
    const [formRegistry, setFormRegistry] = useState ({
        firstName: "",
        lastName: "",
        username: "",
        email: "",
        password: "",
        confirmPassword: ""
    });

    const onChange = (event) => {
        const { name, value } = event.target;
        setFormRegistry({ ...formRegistry, [name]: value});
    }

    const handleSubmitRegistry = async (event) => {
        event.preventDefault();

        setLoading(true);
        
        await resgisterUser(formRegistry);

        setLoading(false);

        navigate("/");

    }

    const validatorInput = () => {
        return validateFirstName(formRegistry.firstName)
            && validateLastName(formRegistry.lastName)
            && validateUsername(formRegistry.username)
            && validateEmail(formRegistry.email)
            && validatePassword(formRegistry.password)
            && validateConfirmPassword(formRegistry.confirmPassword)
    }

    return (

        <Form className="form-credentials" >
            
            <Input name="firstName" type="text" className={className} placeholder="Nome" onChange={onChange}/>
            <Input name="lastName" type="text" className={className} placeholder="Sobrenome" onChange={onChange}/>
            <Input name="username" type="text" className={className} placeholder="Nome de Usuário" onChange={onChange}/>
            <Input name="email" type="text" className={className} placeholder="Email" onChange={onChange}/>
            <Input name="password" type="password" className={className} placeholder="Senha" onChange={onChange} />
            <Input name="confirmPassword" type="password" className={className} placeholder="Confirmar senha" onChange={onChange}/>

            <Button 
                type="button"
                width="100"
                fontSize="1"
                padding="10"
                borderradius="5"
                fontWeight="bold"
                justify="center"
                onClick={handleSubmitRegistry} 
                disabled={loading === true || !validatorInput()}
            > 
                Cadastrar-se
            </Button>
            
        </Form>

    );
};