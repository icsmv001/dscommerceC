package com.devsuperior.dscommerce.dto;

import java.util.Objects;

import com.devsuperior.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	
	private Long id;
	
	@Size(min=3, max=80, message = "nome precisa ter de 3 a 80 caracteres")
	@NotBlank(message= "Campo requerido")
	private String name;
	
	@Size(min=10, message = "Descrição precisa ter no minimo 10 caracteres")
	@NotBlank(message= "Campo requerido")
	private String description;
	
	@Positive(message="O preço dever ser positivo")
	private Double price;
	
	
	private String imgUrl;

	public ProductDTO() {};
	
	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	//contrutor que recebe a entidade
	public ProductDTO(Product entity) {
		super();
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
	}
	
	
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public Double getPrice() {
		return price;
	}


	public String getImgUrl() {
		return imgUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		return Objects.equals(id, other.id);
	}
	

	
	
}
