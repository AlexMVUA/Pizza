package ua.pizzadelivery.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	@Column(name = "customer_id")
	private Long id;
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accum_card_id")	
	private AccumulativeCard accumulativeCard;
	
	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private User user;
	*/
	
	public Customer() {
		
	}
	
	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
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

	public AccumulativeCard getAccumulativeCard() {
		return accumulativeCard;
	}

	public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
		this.accumulativeCard = accumulativeCard;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", accumulativeCard=");
		builder.append(accumulativeCard);
		builder.append("]");
		return builder.toString();
	}
	
	
}
