package com.devsuperior.dscommerce.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


//entidade do sistema dscommerce

@Entity 
@Table(name="tb_order")

public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	
	 //configurar para que seja salvo no banco de dados no padrao UTC-GMT usar notação abaixo
	@Column (columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	 private Instant  moment;
	
	 private OrderStatus status;
	 
	 @ManyToOne
	 @JoinColumn(name ="client_id")
	 private User client;

	 @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	 private Payment payment;
	 
	
	 @OneToMany(mappedBy = "id.order") 
	 private Set<OrderItem> items = new  HashSet<>();
	 
	 
	 public Order() {
		 
	 }




	public Order(Integer id, Instant moment, OrderStatus status, User client, Payment payment) {
		super();
		this.id = id;
		this.moment = moment;
		this.status = status;
		this.client = client;
		this.payment = payment;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Instant getMoment() {
		return moment;
	}




	public void setMoment(Instant moment) {
		this.moment = moment;
	}




	public OrderStatus getStatus() {
		return status;
	}




	public void setStatus(OrderStatus status) {
		this.status = status;
	}




	public User getClient() {
		return client;
	}




	public void setClient(User client) {
		this.client = client;
	}




	public Payment getPayment() {
		return payment;
	}




	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Set<OrderItem> getItems(){
		return items;
	}
	  
	public List<Product> getProducts() {
		return items.stream().map(x-> x.getProduct()).collect(Collectors.toList());
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
 
	 
	 
	 
	 
	}
