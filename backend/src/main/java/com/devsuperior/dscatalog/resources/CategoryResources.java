package com.devsuperior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Category;

@RestController // define que a classse é um controlador REST
@RequestMapping(value = "/categories") // define a rota da requisição
public class CategoryResources {
	// ResponseEntity é um obj do spring que vai encapsular uma resposta HTTP
	@GetMapping
	public ResponseEntity<List<Category>> findall(){
		List<Category> list = new ArrayList<>();
		list.add(new Category(1L, "Books"));
		list.add(new Category(2L, "Eletronics"));
		return ResponseEntity.ok().body(list);
	}

}
