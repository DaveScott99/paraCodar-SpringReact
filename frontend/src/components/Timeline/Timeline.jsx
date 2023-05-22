import React, { useEffect, useState } from "react";

import { IoIosShareAlt } from "react-icons/io"
import { BiCommentDetail } from "react-icons/bi"
import { AiOutlineHeart, AiFillHeart } from "react-icons/ai"
import PostService from "../../services/PostService";
import NewPost from "../NewPost/NewPost";

import './Timeline.css'

const postService = new PostService();

export default function Timeline({ postData }){

        /* useState para armazenar os posts que vem da API */
        const [posts, setPost] = useState([]);

        const [liked, setLiked] = useState(true);
        const [likes, setLikes] = useState(0);

        const clickLike = () => {
            setLiked(!liked);
            if (liked) {
                setLikes(likes + 1);
            }
            else {
                setLikes(likes - 1);
            }

        }

        /* Função para resgatar os POSTS da API e alocar no useState */
        const getPosts = async () => {
            const data = await postService.findAll();
            setPost(data);
        }
    
        /* Função que executará o carregamento do posts sempre que recarregar a página */
        useEffect(() => {
            getPosts();
        }, [])

    return (
        <div className="container-post">
            
            <header className="container-header-timeline">
                <h1>Página Inicial</h1>
            </header>

            <NewPost />

            {posts.map((post) => (
                <div className="post" key={post.id}>
                    
                    <div className="header-post">

                        <div className="user-data">
                            <img src={post.user.userImg} alt=""  className="user-image"/>

                            <div className="user-texts">
                                <span className="user-name">{post.user.name} ·</span>
                                <div className="post-date">{post.instant}</div> 
                            </div>

                        </div>

                    </div>  

                    <div className="text-content-container">
                        <div className="text-contente">
                            { post.body }
                        </div>
                    </div>

                    <div className="img-post">
                        <img src={post.coverImg} alt="Imagem do POST" />
                    </div>

                    <div className="footer-post">

                        <div className="interactios-button">
                            <div className="center-box" onClick={clickLike}>
                                { 
                                    liked
                                        ?
                                        <AiOutlineHeart className="icon-interaction" />
                                        :
                                        <AiFillHeart className="icon-interaction" />
                                } { likes }
                            </div>

                            <div className="center-box">
                                <BiCommentDetail className="icon-interaction"/> 0
                            </div>

                            <div className="center-box">
                                <IoIosShareAlt className="icon-interaction"/>
                            </div>
                        </div>
        
                    </div>
                </div>
        
                ))
            }
        </div>
    );
};