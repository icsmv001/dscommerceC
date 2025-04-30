package com.devsuperior.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ProductService {

	@Autowired
	private ProductRepository  repository;
	
	//metodo de busca no banco de dados
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
 
		
		//tratamento de retorno de erro, colocando o retorno tradado.
		Product product = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso nao encontrado"));
		       return new ProductDTO(product);
	}
	
	
	
	//metodo de busca de lista de produtos
		@Transactional(readOnly = true)
		public Page<ProductDTO> findAll(Pageable pageable) {
			Page<Product> result = repository.findAll(pageable);
			
			return result.map(X -> new ProductDTO(X));
			
		}
		
		
	//metodo inserir novo item (POST)
		@Transactional
		public ProductDTO insert(ProductDTO dto) {
			Product entity = new Product();
		    copyDtoToEntity(dto,entity);
		    entity = repository.save(entity);
			return new ProductDTO(entity);
		}		
	
		
		
		
		
		//metodo alterar item  (PUT)
				@Transactional
				public ProductDTO update(Long id,ProductDTO dto) {
					
					try {
					
						Product entity = repository.getReferenceById(id);
	                    copyDtoToEntity(dto,entity);
					    entity = repository.save(entity);
						return new ProductDTO(entity);
						
					}
					
					catch(EntityNotFoundException e) { 
						throw new  ResourceNotFoundException("Recurso nao encontrado");
						
					}
					
					
				}

				
				private void copyDtoToEntity(ProductDTO dto, Product entity) {
					entity.setName(dto.getName());
					entity.setDescription(dto.getDescription());
					entity.setPrice(dto.getPrice());
					entity.setImgUrl(dto.getDescription());
					
				}	
				
				
				
				
				//METODO DELETE
				@Transactional(propagation = Propagation.SUPPORTS)
				public void delete(Long id) {
					
					if (!repository.existsById(id)) {
						throw new ResourceNotFoundException("Recurso nao encontrado");
					}
					
					try {
						repository.deleteById(id);
					}
					catch (DataIntegrityViolationException e) {
						throw new DatabaseException("Falha de integracao referencial");
					}
					
					
				
				}		
				
		

		
		
	
		
		
	
	
}
