package com.maki.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.maki.api.model.Produto;

public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private double preco;

	public ProdutoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ProdutoDTO(Produto produto) {
		id = produto.getId();
		nome = produto.getNome();
		preco = produto.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
