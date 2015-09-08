package ua.pizzadelivery.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Orders")
@Component(value = "order")
@Scope(value = "prototype" )
public class Order {	
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@Transient
	private List<Pizza> list;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="order_pizza",
			joinColumns=@JoinColumn(name="order_id"))
	@MapKeyJoinColumn(name="pizza_id")
	@Column(name="amount")	
	private Map<Pizza, Integer> pizzas;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Temporal(value=TemporalType.DATE)
	private Date date;
	
	private Double totalCost;

    @Transient
    @Autowired
    private TotalOrderCostCalculator totalOrderCostCalculator;
		
	public Order() {
       date = new Date();
    }  
	
	public Order (Customer customer, Map<Pizza, Integer> pizzas) {
		this.customer = customer;
		this.pizzas = pizzas;		
	}
	
	public Long getId() {
		return id;
	}
		
	public Date getDate() {
        return date;
    }  
	
	public List<Pizza> getList() {
		return list;
	}

	public void setList(List<Pizza> list) {
		this.list = list;
	}

	public Map<Pizza, Integer> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Map<Pizza, Integer> pizzas) {
		this.pizzas = pizzas;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void destroy(){
        System.out.println("Destroy");
    }   
		
	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	public void calcTotalCost() {
        totalCost = 
        		totalOrderCostCalculator.calculateTotalOrderPrice(
        				pizzas, 0.);
    }	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order #");
		sb.append(id);	
		sb.append("\t");
		sb.append(date);
		sb.append("\n");
		
		/*for (Pizza p : list) {
			sb.append(p);
			sb.append("\n");
		}
		*/
		for (Map.Entry<Pizza, Integer> pizza : pizzas.entrySet()) {
			sb.append(pizza.getKey());
			sb.append(" : ");
			sb.append(pizza.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
