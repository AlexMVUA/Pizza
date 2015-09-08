package ua.pizzadelivery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue
	@Column(name = "address_id")
	private Long id;
	private String city;
	private String street;
	private String building;
	private String apartment;
	
	public Address() {
		
	}
	
	public Address(String country, String city, String street, String building, String apartment) {		
		this.city = city;
		this.street = street;
		this.building = building;
		this.apartment = apartment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public String getApartment() {
		return apartment;
	}
	
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [id=");
		builder.append(id);
		builder.append(", city=");
		builder.append(city);
		builder.append(", street=");
		builder.append(street);
		builder.append(", building=");
		builder.append(building);
		builder.append(", apartment=");
		builder.append(apartment);
		builder.append("]");
		return builder.toString();
	}	
}
