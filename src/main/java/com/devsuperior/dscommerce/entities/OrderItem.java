package com.devsuperior.dscommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_order_item")
public class OrderItem {

	
	//para referenciar a classe auxiliar, estanciar manualmente.
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK() ;
	


	private Integer quantity;
	private Double price;
	
	
	public OrderItem() {
		
	}


	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

   // implementando manualmente o get/set para order e product.
   public Order getOrder() {
	   return id.getOrder();
   }

   public void SetOrder(Order order) {
	     id.setOrder(order);
   }
   
   
	public Product getProduct() {
		return id.getProduct();
	}
	   public void setProduct(Product product) {
		   id.setProduct(product);
	   }
	
	
	   
	
	
	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}
	
	



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	
	
	
	
	
}
