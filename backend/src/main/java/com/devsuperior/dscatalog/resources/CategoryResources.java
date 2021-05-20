package com.devsuperior.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

@RestController // define que a classse é um controlador REST
@RequestMapping(value = "/categories") // define a rota da requisição
public class CategoryResources {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findall() { // ResponseEntity é um obj do spring que vai encapsular uma resposta HTTP
		List<CategoryDTO> list = service.findall();
		return ResponseEntity.ok().body(list);
	}

}
