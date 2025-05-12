package it.uniroma3.siwbooks.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Administrator extends User {
	@NotNull
	private Integer adminCode;

	public Integer getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(Integer adminCode) {
		this.adminCode = adminCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		return Objects.equals(adminCode, other.adminCode);
	}
	
	
}
