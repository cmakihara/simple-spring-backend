package com.maki.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.maki.api.dto.CategoriaDTO;
import com.maki.api.model.Categoria;
import com.maki.api.repository.CategoriaRepository;
import com.maki.api.service.exceptions.DataIntegrityException;
import com.maki.api.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List<Categoria> findAll() {		
		return categoriaRepository.findAll();
	}
	
	/**
	 * 
	 * @param categoria
	 * @return
	 */
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
	
	/**
	 * 
	 * @param categoria
	 * @return
	 */
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return categoriaRepository.save(categoria);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
}
