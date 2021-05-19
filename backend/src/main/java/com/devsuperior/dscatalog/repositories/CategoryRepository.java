package com.devsuperior.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;

// componente de injeção de dependencia automatizado pelo spring

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
