package com.astro.paraCodar.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.astro.paraCodar.dto.request.RegisterUserDTO;
import com.astro.paraCodar.dto.request.UriDTO;
import com.astro.paraCodar.dto.request.UserUpdateDTO;
import com.astro.paraCodar.dto.response.UserDTO;
import com.astro.paraCodar.dto.response.UserMinDTO;
import com.astro.paraCodar.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/findAll/")
	public ResponseEntity<Page<UserDTO>> findAllPaged(Pageable pageable){
		Page<UserDTO> users = userService.findAllPaged(pageable);
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping(value = "/searchUserByUsername/{username}")
	public ResponseEntity<Page<UserMinDTO>> searchUserByUsername(Pageable pageable, @PathVariable String username) {
		Page<UserMinDTO> users = userService.searchUserByUsername(pageable, username);
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping(value = "/findByUsername/{username}")
	public ResponseEntity<UserMinDTO> findByUsername(@PathVariable String username) {
		UserMinDTO user = userService.findByUsername(username);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<UserMinDTO> findById(@PathVariable Long id){
		UserMinDTO user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping(value = "/insert")
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody RegisterUserDTO dto) {
		UserDTO user = userService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping(value = "/updateUser/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto){
		UserDTO user = userService.update(id, dto);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping(value = "/upload/profile_photo/{username}")
	public ResponseEntity<UriDTO> uploadProfilePhoto(@RequestParam("file") MultipartFile file, @PathVariable String username) {
		UriDTO dto = userService.uploadProfilePhoto(file, username);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping(value = "/upload/background_user/{username}")
	public ResponseEntity<UriDTO> uploadBackgroundImage(@RequestParam("file") MultipartFile file, @PathVariable String username) {
		UriDTO dto = userService.uploadBackgroundImage(file, username);
		return ResponseEntity.ok().body(dto);
	}
	
}
