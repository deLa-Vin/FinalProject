package com.skilldistillery.skillguild.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Member {

	@ManyToOne
	@JoinColumn(name="user_id")
	private Guild guild;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JoinColumn(name="approved_by")
	private int approvedBy;
	
	
	private boolean moderator;
	
	@JoinColumn(name="created_on")
	@CreationTimestamp
	private LocalDateTime createdOn;

	public Member() {
		super();
	}

	public Guild getGuild() {
		return guild;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public boolean isModerator() {
		return moderator;
	}

	public void setModerator(boolean moderator) {
		this.moderator = moderator;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(user, other.user);
	}

	public Member(Guild guild, User user, int approvedBy, boolean moderator, LocalDateTime createdOn) {
		super();
		this.guild = guild;
		this.user = user;
		this.approvedBy = approvedBy;
		this.moderator = moderator;
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Member [user=" + user + ", approvedBy=" + approvedBy + ", moderator=" + moderator + ", createdOn="
				+ createdOn + "]";
	}
	
	
	
	
}
