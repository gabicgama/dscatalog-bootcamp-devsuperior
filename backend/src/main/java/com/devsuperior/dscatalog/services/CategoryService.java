package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

// essa notação vai registrar a classe como um componente que vai participar do sistema de
// injeção de dependencia automatizado pelo spring

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		/*
		 * converter da lista Category para lista CategoryDTO usando expressão lambda
		 * stream() converter lista pra um stream, pra nos permiter trabalhar com
		 * expressão lambda. A função map() aplica uma função para cada elemento da
		 * lista após isso, é preciso converter novamente para lista com o collect()
		 * 
		 * List<CategoryDTO> listDTO = new ArrayList<>(); for (Category cat : list) {
		 * listDTO.add(new CategoryDTO(cat)); }
		 */
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}

}
