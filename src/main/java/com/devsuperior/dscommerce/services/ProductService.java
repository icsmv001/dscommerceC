package com.devsuperior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;


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
	
	
	
	//metodo de busca de lista de produtos
		@Transactional(readOnly = true)
		public Page<ProductDTO> findAll(Pageable pageable) {
			Page<Product> result = repository.findAll(pageable);
			
			return result.map(X -> new ProductDTO(X));
			
		}
		
	//metodo inserir novo item
		@Transactional
		public ProductDTO insert(ProductDTO dto) {
			Product entity = new Product();
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setPrice(dto.getPrice());
			entity.setImgUrl(dto.getDescription());
		    entity = repository.save(entity);
			return new ProductDTO(entity);
		}		
	
	
	
}
