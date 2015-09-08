package ua.pizzadelivery.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "accumulative_card")
public class AccumulativeCard {
	@Id
	@GeneratedValue

	@Column(name = "accum_card_id")
	private Long id;
	private Double accumulativeSum;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	public AccumulativeCard() {
		this.accumulativeSum = 0.;
	}

	public AccumulativeCard(Address address, Double accumulativeSum) {
		this.address = address;
		this.accumulativeSum = accumulativeSum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAccumulativeSum() {
		return accumulativeSum;
	}

	public void setAccumulativeSum(Double accumulativeSum) {
		this.accumulativeSum = accumulativeSum;
	}

	public void increaseAccumulativeSum(Double increaseValue) {
		this.accumulativeSum += increaseValue;
	}
	
	public void decreaseAccumulativeSum(Double decreaseValue) {
		this.accumulativeSum += decreaseValue;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccumulativeCard [id=");
		builder.append(id);
		builder.append(", accumulativeSum=");
		builder.append(accumulativeSum);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
	
	
}
