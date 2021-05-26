package com.devsuperior.dscatalog.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dscatalog.repositories.ProductRepository;

// @ExtendWith -> notação para testes unitários
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	/*
	 * Não usamos o @AutoWired aqui pq senão vai ser injetado a classe service com
	 * as suas dependencias tbm auto injetadas.
	 */
	@InjectMocks
	private ProductService service;
	
	/* @Mock -> Simular um obj das dependias da classe service */
	@Mock
	private ProductRepository repository;
}
