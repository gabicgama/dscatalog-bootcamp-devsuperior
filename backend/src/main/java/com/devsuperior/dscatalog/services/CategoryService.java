package com.devsuperior.dscatalog.services;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

/*
 * @Service - essa notação vai registrar a classe como um componente que vai participar 
 * do sistema de injeção de dependencia automatizado pelo spring 
 */

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	/*
	 * Converter da lista Category para lista CategoryDTO usando expressão lambda
	 * stream() converter lista pra um stream, pra nos permiter trabalhar com
	 * expressão lambda. A função map() aplica uma função para cada elemento da
	 * lista após isso, é preciso converter novamente para lista com o collect()
	 */

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> list = repository.findAll(pageRequest); // busca no banco de dados (JPA método)
		return list.map(x -> new CategoryDTO(x));
		/*
		 * return list.stream().map(x -> new  CategoryDTO(x)).collect(Collectors.toList()); 
		 * List<CategoryDTO> listDTO = new ArrayList<>(); 
		 * for (Category cat : list) { listDTO.add(new CategoryDTO(cat));}
		 */
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id); // busca no banco de dados por id (JPA método)
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity); // insere no banco de dados (JPA método)
		return new CategoryDTO(entity);
	}

	/*
	 * Numa operação de atualização, se usarmos o findById() iremos acabar
	 * acesssando no BD duas vezes. o findDyId acessa diretamente o BD. O getOne()
	 * não. Ele instancia um obj provisório com os dados. Só quando se salva, que
	 * ele vai no banco de dados.
	 */
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}

}
