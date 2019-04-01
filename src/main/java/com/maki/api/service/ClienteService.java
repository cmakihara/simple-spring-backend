package com.maki.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.maki.api.dto.ClienteDTO;
import com.maki.api.model.Cliente;
import com.maki.api.repository.ClienteRepository;
import com.maki.api.service.exceptions.DataIntegrityException;
import com.maki.api.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newObj = find(cliente.getId());
		updateData(newObj, cliente);
		return clienteRepository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente cliente) {
		newObj.setNome(cliente.getNome());
		newObj.setEmail(cliente.getEmail());
	}

	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}
		

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir possui entidade relacionadas");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
}
