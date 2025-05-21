package it.uniroma3.siwbooks.model;

import java.sql.Blob;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class ImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	private Blob imageData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Blob getImageData() {
		return imageData;
	}

	public void setImageData(Blob imageData) {
		this.imageData = imageData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(imageData);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageEntity other = (ImageEntity) obj;
		return Objects.equals(imageData, other.imageData);
	}

	
	
}
