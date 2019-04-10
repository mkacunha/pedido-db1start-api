package br.com.db1.pedidos.pedidosapi.domain.dto;

import java.io.Serializable;

import br.com.db1.pedidos.pedidosapi.domain.entity.ClienteStatus;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String cpf;

	private ClienteStatus status;

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String nome, String cpf, ClienteStatus status) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ClienteStatus getStatus() {
		return status;
	}

	public void setStatus(ClienteStatus status) {
		this.status = status;
	}
}
