package br.com.techlima.dto;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class AddressDto extends RepresentationModel<AddressDto> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long key;
	private String street;
	private int number;
	private String city;
	private String uf;
	private String cep;
	public AddressDto() {
		
	}
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, city, key, number, street, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDto other = (AddressDto) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(city, other.city) && Objects.equals(key, other.key)
				&& number == other.number && Objects.equals(street, other.street) && Objects.equals(uf, other.uf);
	}
	
	
}
