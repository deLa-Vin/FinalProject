package com.skilldistillery.skillguild.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String description;

	@Column(name = "publish_date")
	private LocalDateTime publishDate;

	@Column(name = "is_public")
	private boolean isPublic;

	@Column(name = "is_live")
	private boolean isLive;

	@UpdateTimestamp
	@Column(name = "last_updated")
	private LocalDateTime lastUpdated;

	@Column(name = "length_minutes")
	private Integer lengthMinutes;

	@Column(name = "presentation_date")
	private LocalDateTime presentationDate;

	@ManyToMany(mappedBy = "contents")
	private List<Resource> resources;
	
	@ManyToOne
	@JoinColumn(name = "guild_id") 
	private Guild guild;

	public Content() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDateTime publishDate) {
		this.publishDate = publishDate;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Integer getLengthMinutes() {
		return lengthMinutes;
	}

	public void setLengthMinutes(Integer lengthMinutes) {
		this.lengthMinutes = lengthMinutes;
	}

	public LocalDateTime getPresentationDate() {
		return presentationDate;
	}

	public void setPresentationDate(LocalDateTime presentationDate) {
		this.presentationDate = presentationDate;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
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
		Content other = (Content) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Content [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", publishDate=").append(publishDate).append(", isPublic=").append(isPublic)
				.append(", isLive=").append(isLive).append(", lastUpdated=").append(lastUpdated)
				.append(", lengthMinutes=").append(lengthMinutes).append(", presentationDate=").append(presentationDate)
				.append("]");
		return builder.toString();
	}

}
