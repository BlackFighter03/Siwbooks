package it.uniroma3.siwbooks.model;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String title;
	
	@NotNull
	private Integer yearPubblication;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<ImageEntity> images;
	
	@ManyToMany
	private Set<Author> authors;
	
	@OneToMany(mappedBy = "book")	
	private List<Survey> surveys;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYearPubblication() {
		return yearPubblication;
	}

	public void setYearPubblication(Integer yearPubblication) {
		this.yearPubblication = yearPubblication;
	}

	public Set<ImageEntity> getImages() {
		return images;
	}

	public void setImages(Set<ImageEntity> images) {
		this.images = images;
	}
	
	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setReviews(List<Survey> surveys) {
		this.surveys = surveys;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, images, title, yearPubblication);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authors, other.authors) && Objects.equals(images, other.images)
				&& Objects.equals(title, other.title) && Objects.equals(yearPubblication, other.yearPubblication);
	}
		
}
