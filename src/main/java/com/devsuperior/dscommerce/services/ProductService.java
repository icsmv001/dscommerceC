package com.devsuperior.dscommerce.services;

import java.util.Optional;


import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

	@Autowired
	private ProductRepository  repository;
	
	//metodo de busca no banco de dados
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional <Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		return dto;
		
	}
	
}
