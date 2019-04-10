package br.com.db1.pedidos.pedidosapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db1.pedidos.pedidosapi.domain.dto.PedidoDTO;
import br.com.db1.pedidos.pedidosapi.domain.dto.PedidoItemDTO;
import br.com.db1.pedidos.pedidosapi.domain.entity.Cliente;
import br.com.db1.pedidos.pedidosapi.domain.entity.Pedido;
import br.com.db1.pedidos.pedidosapi.domain.entity.Produto;
import br.com.db1.pedidos.pedidosapi.repository.ClienteRepository;
import br.com.db1.pedidos.pedidosapi.repository.PedidoRepository;
import br.com.db1.pedidos.pedidosapi.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public PedidoDTO salvar(PedidoDTO dto) {
		String codigoProduto = String.valueOf(System.currentTimeMillis());
		Cliente cliente = clienteRepository.getOne(dto.getIdCliente());
		
		Pedido pedido = new Pedido(codigoProduto, cliente);
		
		for (PedidoItemDTO itemDTO : dto.getItens()) {
			Produto produto = produtoRepository.findByCodigo(itemDTO.getCodigoProduto());
			pedido.adicionarItem(produto, itemDTO.getQuantidade());			
		}
		
		pedidoRepository.save(pedido);
		
		return dto;
	}
}
