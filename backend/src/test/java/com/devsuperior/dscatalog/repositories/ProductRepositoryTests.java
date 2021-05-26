package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;

/* @DataJpaTest -> notação para testes de repository */
@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;

	/*
	 * @BeforeEach -> Notação para executar o método antes da execução dos testes.
	 * Evita a repetição de declaração de variáveis.
	 */
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}

	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Product product = Factory.createProduct();
		product.setId(null);
		product = repository.save(product);
		Optional<Product> obj = repository.findById(product.getId());
		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts + 1L, product.getId());
		Assertions.assertTrue(obj.isPresent());
		Assertions.assertSame(obj.get(), product);
	}

	@Test
	public void findByIdShouldReturnOptionalNonEmptyWhenIdExists() {
		Optional<Product> obj = repository.findById(existingId);
		Assertions.assertTrue(obj.isPresent());
	}

	@Test
	public void findByIdShouldReturnOptionalEmptyWhenIdNotExists() {
		Optional<Product> obj = repository.findById(nonExistingId);
		Assertions.assertTrue(obj.isEmpty());
	}

	@Test
	public void deleteShouldDeleteObjectWhenExists() {
		repository.deleteById(existingId);
		Optional<Product> obj = repository.findById(1L);
		Assertions.assertFalse(obj.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}

}
