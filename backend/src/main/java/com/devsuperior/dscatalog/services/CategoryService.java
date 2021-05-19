package com.devsuperior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

// essa notação vai registrar a classe como um componente que vai participar do sistema de
// injeção de dependencia automatizado pelo spring

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findall() {
		return repository.findAll();
	}

}
