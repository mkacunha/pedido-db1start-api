package br.com.db1.pedidos.pedidosapi.domain.dto;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idCliente;
	
	private List<PedidoItemDTO> itens;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<PedidoItemDTO> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItemDTO> itens) {
		this.itens = itens;
	}
}
