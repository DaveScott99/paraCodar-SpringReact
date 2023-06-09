package com.astro.paraCodar.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name="FIRST_NAME", nullable = false, length = 120)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable = false, length = 120)
	private String lastName;
	
	@Column(name="USERNAME", unique = true, nullable = false, length = 150)
	private String username;
	
	@Column(name="BIOGRAPHY", nullable = true, length = 255)
	private String biography;
	
	@Column(name="TITLE", nullable = true, length = 120)
	private String title;
	
	@Column(name="GITHUB_LINK", nullable = true, length = 255)
	private String gitHubLink;
	
	@Column(name="LINKEDIN_LINK", nullable = true, length = 255)
	private String linkedinLink;
	
	@Column(name="INSTAGRAM_LINK", nullable = true, length = 255)
	private String instagramLink;
	
	@Column(name="BACKGROUND_IMAGE", nullable = true, length = 255)
	private String backgroundImage;
	
	@Column(name="PROFILE_PHOTO", nullable = true, columnDefinition = "TEXT")
	private String profilePhoto;
	
	@Column(name="EMAIL", unique = true, nullable = false, updatable = false)
	private String email;
	
	@Column(name="PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "REGISTRATION_MOMENT", nullable = false)
	@CreationTimestamp
	private Instant registrationMoment;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Post> posts;
	
	@ManyToMany(mappedBy = "likes", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Post> likedPosts = new HashSet<>();
	
	public User() {
	}
	
	public User(Long id, String firstName, String lastName, String username, String biography, String title,
			String gitHubLink, String linkedinLink, String instagramLink, String backgroundImage, String profilePhoto,
			String email, String password, Instant registrationMoment) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.biography = biography;
		this.title = title;
		this.gitHubLink = gitHubLink;
		this.linkedinLink = linkedinLink;
		this.instagramLink = instagramLink;
		this.backgroundImage = backgroundImage;
		this.profilePhoto = profilePhoto;
		this.email = email;
		this.password = password;
		this.registrationMoment = registrationMoment;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGitHubLink() {
		return gitHubLink;
	}

	public void setGitHubLink(String gitHubLink) {
		this.gitHubLink = gitHubLink;
	}

	public String getLinkedinLink() {
		return linkedinLink;
	}

	public void setLinkedinLink(String linkedinLink) {
		this.linkedinLink = linkedinLink;
	}

	public String getInstagramLink() {
		return instagramLink;
	}

	public void setInstagramLink(String instagramLink) {
		this.instagramLink = instagramLink;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public void setLikedPosts(Set<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Instant getRegistrationMoment() {
		return registrationMoment;
	}

	public void setRegistrationMoment(Instant registrationMoment) {
		this.registrationMoment = registrationMoment;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Set<Post> getLikedPosts() {
		return likedPosts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
}
