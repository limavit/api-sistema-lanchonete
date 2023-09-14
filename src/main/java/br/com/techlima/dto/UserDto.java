package br.com.techlima.dto;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","firstName", "lastName","document","idAddress"})
public class UserDto extends RepresentationModel<UserDto> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long key;
	private String firstName;
	private String lastName;
	private String document;
	private Long idAddress;
	public UserDto() {
	}
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
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
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public Long getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(Long idAddress) {
		this.idAddress = idAddress;
	}

	@Override
	public int hashCode() {
		return Objects.hash(document, firstName, idAddress, key, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(document, other.document) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(idAddress, other.idAddress) && Objects.equals(key, other.key)
				&& Objects.equals(lastName, other.lastName);
	}
	
	
}
