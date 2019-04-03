package br.com.db1.pedidos.pedidosapi.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Verificador;

public class Pedido {
	
	private static final int QUANTIDADE_MAXIMA_ITENS = 20;
	
	private String codigo;
	
	private PedidoStatus status;
	
	private LocalDateTime dataStatus;
	
	private Cliente cliente;
	
	@OneToMany(mappedBy="pedido", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PedidoHistorico> historicos = new ArrayList<>();
	
	private List<PedidoItem> itens = new ArrayList<>();

	public Pedido(String codigo, Cliente cliente) {
		Verificador.naoNulo(codigo, "c�digo");
		Verificador.naoNulo(cliente, "cliente");
		this.verificarClienteAtivo();
		
		
		this.codigo = codigo;
		this.cliente = cliente;
		this.novoHistoricoStatus();
	}

	public void adicionarItem(Produto produto, Double quantidade) {
		this.verificarStatusPedidoParaAlterar();
		
		if (this.itens.size() == QUANTIDADE_MAXIMA_ITENS) {
			throw new RuntimeException("Quantidade m�xima de itens excedida: " + QUANTIDADE_MAXIMA_ITENS);
		}
		
		this.itens.add(new PedidoItem(produto, quantidade));
	}
	
	public void removerItem(Produto produto) {
		this.verificarStatusPedidoParaAlterar();
		this.itens.removeIf(item -> item.getProduto().getCodigo().equals(produto.getCodigo()));
	}
	
	public void faturar() {
		if (!PedidoStatus.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido est�  " + this.status);
		}
		
		if (this.itens.size() == 0 || this.itens.size() > QUANTIDADE_MAXIMA_ITENS) {
			throw new RuntimeException("Pedido deve ter no min�mo 1 item e no m�ximo 10 itens. Quantidade atual: " + this.itens.size());
		}
		
		this.verificarClienteAtivo();
		
		this.status = PedidoStatus.FATURADO;
		this.novoHistoricoStatus();
	}
	
	public void cancelar() {
		this.verificarStatusPedidoParaAlterar();		
		this.status = PedidoStatus.CANCELADO;
		this.novoHistoricoStatus();
	}
	
	public void reabrir() {
		if (!PedidoStatus.CANCELADO.equals(this.status)) {
			throw new RuntimeException("Pedido est� " + this.status);
		}
		
		this.status = PedidoStatus.ABERTO;
		this.novoHistoricoStatus();
	}
	
	public String getCodigo() {
		return codigo;
	}

	public PedidoStatus getStatus() {
		return status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<PedidoHistorico> getHistoricos() {
		return Collections.unmodifiableList(historicos);		
	}

	public List<PedidoItem> getItens() {
		return Collections.unmodifiableList(itens);
	}
	
	public LocalDateTime getDataStatus() {
		return dataStatus;
	}
	
	private void novoHistoricoStatus() {
		PedidoHistorico historico = new PedidoHistorico(this, this.status);		
		this.historicos.add(historico);
		this.dataStatus = historico.getData();
	}
	
	private void verificarClienteAtivo() {
		if (!cliente.isAtivo()) {
			throw new RuntimeException("Cliente " + cliente.getNome() + " est� inativo");
		}
	}
	
	private void verificarStatusPedidoParaAlterar() {
		if (!PedidoStatus.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido est�  " + this.status);
		}
	}
}
