package it.uniroma3.siwbooks.model;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

import it.uniroma3.siwbooks.constant.Nationality;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Author {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	@NotNull
	private LocalDate dateBirth;
	
	@PastOrPresent
	private LocalDate dateDeath;
	@NotNull
	private Nationality nationality;
	
	@NotNull
	@OneToOne
	private ImageEntity photo;
	
	@ManyToMany(mappedBy = "authors")
	private List<Book> books;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public LocalDate getDateDeath() {
		return dateDeath;
	}

	public void setDateDeath(LocalDate dateDeath) {
		this.dateDeath = dateDeath;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public ImageEntity getPhoto() {
		return photo;
	}

	public void setPhoto(ImageEntity photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateBirth, dateDeath, name, nationality, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(dateBirth, other.dateBirth) && Objects.equals(dateDeath, other.dateDeath)
				&& Objects.equals(name, other.name) && nationality == other.nationality
				&& Objects.equals(surname, other.surname);
	}
	
	
}
