package com.maki.curso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maki.curso.model.Categoria;
import com.maki.curso.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Optional<Categoria> buscar(Integer id) {
		
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj;
	}

}
