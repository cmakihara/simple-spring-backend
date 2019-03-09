package com.maki.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.maki.api.model.Categoria;
import com.maki.api.repository.CategoriaRepository;
import com.maki.api.service.exceptions.DataIntegrityException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Categoria> findById(Integer id) {		
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj;
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
		findById(categoria.getId());
		return categoriaRepository.save(categoria);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

}
