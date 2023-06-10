import React, { useContext, useState } from "react";
import Button from "../../Generics/Button/Button";
import { validateTextPost } from "../../../utils/Validators";
import { AuthContext } from "../../../contexts/Auth/AuthContext";
import { publishPost } from "../../../services/Api";
import { Avatar } from "@mui/material";
import Input from "../../Generics/Input/Input"

import './NewPost.css';

export default function NewPost() {
    const { user } = useContext(AuthContext);

    const [loading, setLoading] = useState(true);
    const [post, setPost] = useState({
        body: '',
        user: {
            id: user.id
        }
    });

    /*Função para resgatar o oque foi digitado pelo usuário nos INPUTS, referenciando
    sempre pelo NAME do input e o seu valor */
    const onChange = (event) => {
        setLoading(false);
        const { name, value } = event.target;
        setPost({ ...post, [name]: value});
    }

    const handleResize = (event) => {
        event.target.style.height = 'auto';
        event.target.style.height = `${event.target.scrollHeight}px`;
    }

    const insertPost = async () => {
        await publishPost(post);
        window.location.reload();
    }

    const validatorInput = () => {
        return validateTextPost(post.body);
    }

    return(
        <div className="container-create">
            <div className="header-new-post">
                <div className="user-image">
                    <Avatar alt="User image" src={user.profilePhoto} sx={{ width: 40, height: 40 }} />
                </div>
            </div>

            <Input 
                name="new-post"
                type="button"
                value="No que está pensando ?"
                className="new-post-button"
            />

            {/*
            <div className="body">
                <div className="text-area-container">
                    <textarea 
                        name="body" 
                        placeholder="O que está pensando?" 
                        className="text-area" 
                        onChange={onChange} 
                        onInput={handleResize}
                    />
                </div>

                <div className="footer">
                    <div className="footer-container">
                        <Button 
                            type="submit" 
                            text="Criar" 
                            className="button-create"
                            onClick={insertPost} 
                            disabled={loading === true || !validatorInput()}
                        />
                    </div>
                </div>
            </div>
             */}
        </div>
    );
}