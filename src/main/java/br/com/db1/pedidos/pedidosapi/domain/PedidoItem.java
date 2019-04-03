package br.com.db1.pedidos.pedidosapi.domain;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Verificador;

public class PedidoItem {
	
	private Produto produto;
	
	private Double quantidade;
	
	private Double valorUnitario;

	public PedidoItem(Produto produto, Double quantidade) {
		Verificador.naoNulo(produto, "produto");
		Verificador.naoNulo(quantidade, "quantidade");
		Verificador.maiorQueZero(quantidade, "quantidade");
		
		if (!produto.isAtivo()) {
			throw new RuntimeException("Produto " + produto.getNome() + " est� " + produto.getStatus());
		}
		
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = produto.getValor();
	}

	public Produto getProduto() {
		return produto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}
	
	public Double getValorTotal() {
		return this.quantidade * this.valorUnitario;
	}
}
