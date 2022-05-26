package com.skilldistillery.skillguild.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="in_reply_to_id")
	private Integer inReplyTo;
	
	@Column(name="text_content")
	private String textContent;
	
	@Column(name="has_been_edited")
	private boolean edited;
	
	@Column(name="created_on")
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@Column(name="last_updated")
	@UpdateTimestamp
	private LocalDateTime lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="content_id")
	private Content content;
	
	public Comment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(int inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
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
		Comment other = (Comment) obj;
		return id == other.id;
	}

	public Comment(int id, int inReplyTo, String textContent, boolean edited, LocalDateTime createdOn,
			LocalDateTime lastUpdated, User user, Content content) {
		super();
		this.id = id;
		this.inReplyTo = inReplyTo;
		this.textContent = textContent;
		this.edited = edited;
		this.createdOn = createdOn;
		this.lastUpdated = lastUpdated;
		this.user = user;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", inReplyTo=" + inReplyTo + ", textContent=" + textContent + ", edited=" + edited
				+ ", createdOn=" + createdOn + ", lastUpdated=" + lastUpdated + ", user=" + user + ", content="
				+ content + "]";
	}

	

	
	
	
	
	
}
