package br.com.techlima.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="t_address")
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	//obs o sistema foi concebido para ser utilizado no Brasil por isso nao possui atributo pais
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	private String street;
	private int number;
	private String city;
	private String uf;
	private String cep;
	public Address() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(cep, city, id, number, street, uf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(city, other.city) && Objects.equals(id, other.id)
				&& number == other.number && Objects.equals(street, other.street) && Objects.equals(uf, other.uf);
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
	

}
