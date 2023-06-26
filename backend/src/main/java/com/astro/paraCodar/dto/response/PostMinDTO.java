package com.astro.paraCodar.dto.response;

import com.astro.paraCodar.entities.Post;

public class PostMinDTO {
	
	private Long id;
	private String body;
	
	public PostMinDTO() {
	}
		
	public PostMinDTO(Post entity) {
		id = entity.getId();
		body = entity.getBody();
	}

	public Long getId() {
		return id;
	}

	public String getBody() {
		return body;
	}
	
}
