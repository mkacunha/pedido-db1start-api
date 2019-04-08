package br.com.db1.pedidos.pedidosapi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Verificador;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 30)
	private ClienteStatus status;

	public Cliente(String nome, String cpf) {
		Verificador.naoNulo(nome, "nome do cliente");
		Verificador.naoNulo(cpf, "CPF do cliente");
		Verificador.cpf(cpf);

		this.nome = nome;
		this.cpf = cpf;
		this.status = ClienteStatus.ATIVO;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public ClienteStatus getStatus() {
		return status;
	}

	public boolean isAtivo() {
		return ClienteStatus.ATIVO.equals(this.status);
	}
}
