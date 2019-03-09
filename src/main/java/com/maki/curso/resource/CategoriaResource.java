package com.maki.curso.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maki.curso.model.Categoria;
import com.maki.curso.service.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Categoria>> find(@PathVariable Integer id ) throws ObjectNotFoundException {
		Optional<Categoria> obj  = categoriaService.buscar(id);			
		return ResponseEntity.ok().body(obj);
	}
	
	

}
